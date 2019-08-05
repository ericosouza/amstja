package br.com.cadastro.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
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

import br.com.cadastro.codec.PessoaCodec;
import br.com.cadastro.model.Email;
import br.com.cadastro.model.Endereco;
import br.com.cadastro.model.Filho;
import br.com.cadastro.model.Pessoa;
import br.com.cadastro.model.RendaMensal;
import br.com.cadastro.model.Telefone;

@Repository
public class PessoaRepository {
	
	@Value("#{systemEnvironment['DB_MONGO_PORTA']}")
	private String porta;
	
	private Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	private MongoClient cliente;
	private MongoDatabase bancoDeDados;
	private MongoCollection<Pessoa> collection;
	
	public void criarConexao() {

		this.codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		PessoaCodec pessoaCodec = new PessoaCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(pessoaCodec));

		ServerAddress endereco = new ServerAddress("mongo71-farm76.kinghost.net", 27017);

		String usuario = "amstja01";
		String senha = "Joisse8789";
		String bancoDados = usuario;

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registro).build();
		MongoCredential credential = MongoCredential.createCredential(usuario, bancoDados, senha.toCharArray());

		this.cliente = new MongoClient(endereco, credential, options);
		this.bancoDeDados = cliente.getDatabase(bancoDados);

		this.collection = this.bancoDeDados.getCollection("Pessoa", Pessoa.class);
		

	}
	
	private void fecharConexao() {
		this.cliente.close();
	}

	
	public Boolean salvar(Pessoa pessoa) {

		criarConexao();

		if (pessoa.getId() == null) {
			collection.insertOne(pessoa);
		} else {
			collection.updateOne(Filters.eq("_id", pessoa.getId()),
					new Document("$set", pessoa));
		}

		fecharConexao();

		return true;
	}
	
	private List<Pessoa> popular(MongoCursor<Pessoa> resultados) {

		List<Pessoa> listaPerfil = new ArrayList<>();
		while (resultados.hasNext()) {
			listaPerfil.add(resultados.next());
		}

		return listaPerfil;
	}
	
	
	public List<Pessoa> obterTodos() {

		criarConexao();

		Document query = new Document();

		MongoCursor<Pessoa> resultados = this.collection.find(query).iterator();

		fecharConexao();

		List<Pessoa> result = popular(resultados);

		return result;
	}
	
	public Pessoa obterPessoaPorID(String id){
		  criarConexao();
		  Pessoa pessoa = this.collection.find(Filters.eq("_id", new ObjectId(id))).first();
		  fecharConexao();
		  return pessoa;
	}
	
	public Pessoa adicionaEndereco(Pessoa pessoa, Endereco endereco) {
		pessoa.getEnderecos().add(endereco);
		return pessoa;
	}
	
	public Pessoa adicionaTelefone(Pessoa pessoa, Telefone telefone) {
		pessoa.getTelefones().add(telefone);
		return pessoa;
	}
	
	public Pessoa adicionaFilho(Pessoa pessoa, Filho filho) {
		pessoa.getFilhos().add(filho);
		return pessoa;
	}
	
	public Pessoa adicionaRenda(Pessoa pessoa, RendaMensal renda) {
		pessoa.getRendas().add(renda);
		return pessoa;
	}
	
	public Pessoa adicionaEmail(Pessoa pessoa, Email email) {
		pessoa.getEmails().add(email);
		return pessoa;
	}

}
