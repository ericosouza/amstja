package br.com.cadastro.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.cadastro.codec.UsuarioCodec;
import br.com.cadastro.model.Usuario;

@Repository
public class UsuarioRepository {

	@Value("#{systemEnvironment['DB_MONGO_PORTA']}")
	private String porta;
	
	private Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	private MongoClient cliente;
	private MongoDatabase bancoDeDados;
	private MongoCollection<Usuario> collection;
	
	public void criarConexao() {

		this.codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		UsuarioCodec usuarioCodec = new UsuarioCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(usuarioCodec));

		ServerAddress endereco = new ServerAddress("mongo71-farm76.kinghost.net", 27017);

		String usuario = "amstja01";
		String senha = "Joisse8789";
		String bancoDados = usuario;

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registro).build();
		MongoCredential credential = MongoCredential.createCredential(usuario, bancoDados, senha.toCharArray());

		this.cliente = new MongoClient(endereco, credential, options);
		this.bancoDeDados = cliente.getDatabase(bancoDados);

		this.collection = this.bancoDeDados.getCollection("Usuario", Usuario.class);
	}
	
	private void fecharConexao() {
		this.cliente.close();
	}
	
	public Usuario buscarPorEmail(String email) {
		criarConexao();
		Usuario usuario = this.collection.find(Filters.eq("email", email)).first();
		fecharConexao();
		return usuario;
	}
	
	public Boolean salvar(Usuario usuario) {
		criarConexao();
		
		try {
			if(usuario.getId() == null) {
				collection.insertOne(usuario);
			}else {
				collection.updateOne(Filters.eq("_id", usuario.getId()), 
						new Document("$set", usuario));
			}
			fecharConexao();
			return true;
		} catch (Exception e) {
			fecharConexao();
			return false;
		} 
	}
	
	public List<Usuario> listar(){
		criarConexao();
		Document query = new Document();
		MongoCursor<Usuario> result = this.collection.find(query).iterator();
		fecharConexao();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		while(result.hasNext()) {
			usuarios.add(result.next());
		}
		return usuarios;
	}
}
