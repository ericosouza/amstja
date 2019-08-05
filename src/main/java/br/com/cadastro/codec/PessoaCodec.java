package br.com.cadastro.codec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import br.com.cadastro.model.Conjugue;
import br.com.cadastro.model.Email;
import br.com.cadastro.model.Endereco;
import br.com.cadastro.model.Filho;
import br.com.cadastro.model.Pessoa;
import br.com.cadastro.model.RendaMensal;
import br.com.cadastro.model.Telefone;

public class PessoaCodec implements CollectibleCodec<Pessoa> {

	private Codec<Document> codec;

	public PessoaCodec(Codec<Document> c) {
		this.codec = c;
	}

	@Override
	public void encode(BsonWriter writer, Pessoa pessoa, EncoderContext encoderContext) {

		ObjectId id = pessoa.getId();
		String nome = pessoa.getNome();
		String nomePai = pessoa.getNomePai();
		String nomeMae = pessoa.getNomeMae();
		Date dataNascimento = pessoa.getDataNascimento();
		String naturalidade = pessoa.getNaturalidade();
		String profissao = pessoa.getProfissao();
		String cpf = pessoa.getCpf();
		String rg = pessoa.getRg();
		String ssp = pessoa.getSsp();
		String tituloEleitor = pessoa.getTituloEleitor();
		String estadoCivil = pessoa.getEstadoCivil();
		String registroComunhao = pessoa.getRegistroComunhao();
		Date dataCasamento = pessoa.getDataCasamento();

		Document docConjugue = new Document();
		
		docConjugue.put("nome", pessoa.getConjugue().getNome());
		docConjugue.put("cpf", pessoa.getConjugue().getCpf());
		docConjugue.put("dataNascimento", pessoa.getConjugue().getDataNascimento());
		docConjugue.put("id", pessoa.getConjugue().getId());
		docConjugue.put("profissao", pessoa.getConjugue().getProfissao());
		docConjugue.put("rg", pessoa.getConjugue().getRg());
		docConjugue.put("ssp", pessoa.getConjugue().getSsp());

		Document documento = new Document();

		documento.put("_id", id);
		documento.put("nome", nome);
		documento.put("nomePai", nomePai);
		documento.put("nomeMae", nomeMae);
		documento.put("dataNascimento", dataNascimento);
		documento.put("naturalidade", naturalidade);
		documento.put("profissao", profissao);
		documento.put("cpf", cpf);
		documento.put("rg", rg);
		documento.put("ssp", ssp);
		documento.put("tituloEleitor", tituloEleitor);
		documento.put("estadoCivil", estadoCivil);
		documento.put("registroComunhao", registroComunhao);
		documento.put("dataCasamento", dataCasamento);
		
		documento.put("conjugue", docConjugue);

		escreverListaEndereco(pessoa, documento);
	
		escreverListaTelefone(pessoa, documento);

		escreverListaEmail(pessoa, documento);

		escreverListaFilho(pessoa, documento);	
		
		escreverListaRenda(pessoa, documento);

		codec.encode(writer, documento, encoderContext);

	}

	private void escreverListaTelefone(Pessoa pessoa, Document documento) {

		List<Document> telefonesDocument = new ArrayList<>();

		for (Telefone item : pessoa.getTelefones()) {

			if (item.getId() == null) {
				item.criaId();
			}

			telefonesDocument.add(new Document("_id", item.getId())
					.append("descricao", item.getDescricao())
					.append("numero", item.getNumero()));

		}

		documento.put("telefones", telefonesDocument);
	}

	private void escreverListaRenda(Pessoa pessoa, Document documento) {

		List<Document> rendaDocument = new ArrayList<>();

		for (RendaMensal item : pessoa.getRendas()) {

			if (item.getId() == null) {
				item.criaId();
			}

			rendaDocument.add(new Document("_id", item.getId())
					.append("descricao", item.getDescricao())
					.append("valor",item.getValor()));

		}

		documento.put("rendas", rendaDocument);
	}

	private void escreverListaFilho(Pessoa pessoa, Document documento) {

		List<Document> filhosDocument = new ArrayList<>();

		for (Filho item : pessoa.getFilhos()) {

			if (item.getId() == null) {
				item.criaId();
			}

			filhosDocument.add(new Document("_id", item.getId())
					.append("nome", item.getNome())
					.append("dataNascimento",item.getDataNascimento()));

		}

		documento.put("filhos", filhosDocument);
	}

	private void escreverListaEmail(Pessoa pessoa, Document documento) {

		List<Document> emailDocument = new ArrayList<>();

		for (Email item : pessoa.getEmails()) {

			if (item.getId() == null) {
				item.criaId();
			}

			emailDocument.add(new Document("_id", item.getId()).append("endereco", item.getEndereco()));

		}

		documento.put("emails", emailDocument);
	}

	private void escreverListaEndereco(Pessoa pessoa, Document documento) {

		List<Document> enderecosDocument = new ArrayList<>();

		for (Endereco item : pessoa.getEnderecos()) {

			if (item.getId() == null) {
				item.criaId();
			}

			enderecosDocument.add(new Document("_id", item.getId())
					.append("descricao", item.getDescricao())
					.append("bairro", item.getBairro().toUpperCase())
					.append("cidade", item.getCidade().toUpperCase())
					.append("cep", item.getCep().toUpperCase())
					.append("uf", item.getUf()));
		}

		documento.put("enderecos", enderecosDocument);
	}

	@Override
	public Class<Pessoa> getEncoderClass() {
		return Pessoa.class;
	}

	@Override
	public Pessoa decode(BsonReader reader, DecoderContext decoder) {
		
		Document document = codec.decode(reader, decoder);

		BasicDBObject basicDBObject = new BasicDBObject(BasicDBObject.parse(document.toJson()));
		
		Pessoa pessoa = new Pessoa();
		
		pessoa = PessoaCodec.converterToPerfil(basicDBObject);
		
		return pessoa;
	}

	@Override
	public Pessoa generateIdIfAbsentFromDocument(Pessoa document) {
		return documentHasId(document) ? document.criaId() : document;
	}

	@Override
	public boolean documentHasId(Pessoa document) {
		return (document.getId() == null);
	}

	@Override
	public BsonValue getDocumentId(Pessoa document) {
		if (!documentHasId(document)) {
			throw new IllegalStateException("Documento sem ID");
		}
		return new BsonString(document.getId().toHexString());
	}
	
	@SuppressWarnings("unchecked")
	public static Pessoa converterToPerfil(DBObject dbo) {

		Pessoa pessoa = new Pessoa();
		
		pessoa.setId((ObjectId) dbo.get("_id"));
		pessoa.setNome((String) dbo.get("nome"));
		pessoa.setNomeMae((String) dbo.get("nomeMae"));
		pessoa.setNomePai((String) dbo.get("nomePai"));
		pessoa.setCpf((String) dbo.get("cpf"));
		pessoa.setDataNascimento((Date) dbo.get("dataNascimento"));
		pessoa.setNaturalidade((String) dbo.get("naturalidade"));
		pessoa.setDataCasamento((Date) dbo.get("dataCasamento"));
		pessoa.setEstadoCivil((String) dbo.get("estadoCivil"));
		pessoa.setProfissao((String) dbo.get("profissao"));
		pessoa.setRegistroComunhao((String) dbo.get("registroComunhao"));
		pessoa.setRg((String) dbo.get("rg"));
		pessoa.setSsp((String) dbo.get("ssp"));
		pessoa.setTituloEleitor((String) dbo.get("tituloEleitor"));
		
		BasicDBObject conj = (BasicDBObject) dbo.get("conjugue");
		Conjugue conjugue = new Conjugue();
		
		conjugue.setId(conj.getObjectId("_id"));
		conjugue.setNome((String) dbo.get("nome"));
		conjugue.setCpf((String) dbo.get("cpf"));
		conjugue.setDataNascimento((Date) dbo.get("dataNascimento"));
		conjugue.setProfissao((String) dbo.get("profissao"));
		conjugue.setRg((String) dbo.get("rg"));
		conjugue.setSsp((String) dbo.get("ssp"));
		
		pessoa.setConjugue(conjugue);
		
		
		List<BasicDBObject> enderecosDocument = (List<BasicDBObject>) dbo.get("enderecos");
		List<Endereco> listaEndereco = new ArrayList<Endereco>();

		enderecosDocument.forEach(a -> {

			Endereco endereco = new Endereco();

			endereco.setId(a.getObjectId("_id"));
			endereco.setBairro((String) a.get("bairro"));
			endereco.setCep((String) a.get("cep"));
			endereco.setCidade((String) a.get("cidade"));
			endereco.setDescricao((String) a.get("descricao"));
			endereco.setUf((String) a.get("uf"));

			listaEndereco.add(endereco);
		});

		pessoa.setEnderecos(listaEndereco);
		
		
		List<BasicDBObject> telefonesDocument = (List<BasicDBObject>) dbo.get("telefones");
		List<Telefone> listaTelefones = new ArrayList<Telefone>();

		telefonesDocument.forEach(a -> {

			Telefone telefone = new Telefone();

			telefone.setId(a.getObjectId("_id"));
			telefone.setDescricao((String) a.get("descricao"));
			telefone.setNumero((String) a.get("numero"));

			listaTelefones.add(telefone);
		});

		pessoa.setTelefones(listaTelefones);
		
		
		List<BasicDBObject> emailsDocument = (List<BasicDBObject>) dbo.get("emails");
		List<Email> listaEmail = new ArrayList<Email>();

		emailsDocument.forEach(a -> {

			Email email = new Email();

			email.setId(a.getObjectId("_id"));
			email.setEndereco((String) a.get("endereco"));

			listaEmail.add(email);
		});

		pessoa.setEmails(listaEmail);
		
		List<BasicDBObject> filhosDocument = (List<BasicDBObject>) dbo.get("filhos");
		List<Filho> listaFilhos = new ArrayList<Filho>();

		filhosDocument.forEach(a -> {

			Filho filho = new Filho();

			filho.setId(a.getObjectId("_id"));
			filho.setDataNascimento((Date) dbo.get("dataNascimento"));
			filho.setNome((String) a.get("nome"));

			listaFilhos.add(filho);
		});

		pessoa.setFilhos(listaFilhos);
		
		
		List<BasicDBObject> rendasDocument = (List<BasicDBObject>) dbo.get("rendas");
		List<RendaMensal> listaRendas = new ArrayList<RendaMensal>();

		rendasDocument.forEach(a -> {

			RendaMensal renda = new RendaMensal();

			renda.setId(a.getObjectId("_id"));
			renda.setDescricao((String) a.get("descricao"));
			renda.setValor((Double) a.get("valor"));

			listaRendas.add(renda);
		});

		pessoa.setRendas(listaRendas);
		


		return pessoa;
	}
	
	

}
