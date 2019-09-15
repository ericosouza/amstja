package br.com.cadastro.codec;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.mongodb.BasicDBObject;

import br.com.cadastro.model.Usuario;

public class UsuarioCodec implements CollectibleCodec<Usuario> {

	private Codec<Document> codec;
	
	public UsuarioCodec(Codec<Document> codec) {
		this.codec = codec;
	}
	
	@Override
	public void encode(BsonWriter writer, Usuario usuario, EncoderContext encoderContext) {
		
		Document documento = new Document();
		
		documento.put("_id", usuario.getId());
		documento.put("nome", usuario.getNome());
		documento.put("email", usuario.getEmail());
		documento.put("senha", usuario.getSenha());
		documento.put("perfil", usuario.getPerfil());
		
		codec.encode(writer, documento, encoderContext);
	}

	@Override
	public Class<Usuario> getEncoderClass() {
		return Usuario.class;
	}

	@Override
	public Usuario decode(BsonReader reader, DecoderContext decoder) {
		Document document = codec.decode(reader, decoder);
		BasicDBObject basic = new BasicDBObject(BasicDBObject.parse(document.toJson()));
		Usuario usuario = new Usuario();
		usuario = UsuarioCodec.converter(basic);
		return usuario;
	}
	
	

	private static Usuario converter(BasicDBObject basic) {
		Usuario usuario = new Usuario();
		
		usuario.setId(basic.getObjectId("_id"));
		usuario.setNome(basic.getString("nome"));
		usuario.setEmail(basic.getString("email"));
		usuario.setSenha(basic.getString("senha"));
		usuario.setPerfil("ROLE_ADMIN");
		return usuario;
	}

	@Override
	public Usuario generateIdIfAbsentFromDocument(Usuario document) {
		return documentHasId(document) ? document.criaId() : document;
	}

	@Override
	public boolean documentHasId(Usuario document) {
		return (document.getId() == null);
	}

	@Override
	public BsonValue getDocumentId(Usuario document) {
		if (!documentHasId(document)) {
			throw new IllegalStateException("Documento sem ID");
		}
		return new BsonString(document.getId().toHexString());
	}

}
