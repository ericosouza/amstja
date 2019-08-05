package br.com.cadastro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.cadastro.dto.ConjugueDto;
import br.com.cadastro.dto.EmailDto;
import br.com.cadastro.dto.EnderecoDto;
import br.com.cadastro.dto.FilhoDto;
import br.com.cadastro.dto.PessoaDto;
import br.com.cadastro.dto.RendaMensalDto;
import br.com.cadastro.dto.TelefoneDto;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = -7820627004183090128L;

	ObjectId id;
	String nome;
	String nomePai;
	String nomeMae;
	Date dataNascimento;
	String naturalidade;
	String profissao;
	String cpf;
	String rg;
	String ssp;
	String tituloEleitor;
	String estadoCivil;
	String registroComunhao;
	Date dataCasamento;

	Conjugue conjugue;

	List<Endereco> enderecos;
	List<Telefone> telefones;
	List<Email> emails;
	List<Filho> filhos;
	List<RendaMensal> rendas;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSsp() {
		return ssp;
	}

	public void setSsp(String ssp) {
		this.ssp = ssp;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getRegistroComunhao() {
		return registroComunhao;
	}

	public void setRegistroComunhao(String registroComunhao) {
		this.registroComunhao = registroComunhao;
	}

	public Date getDataCasamento() {
		return dataCasamento;
	}

	public void setDataCasamento(Date dataCasamento) {
		this.dataCasamento = dataCasamento;
	}

	public Conjugue getConjugue() {
		return conjugue;
	}

	public void setConjugue(Conjugue conjugue) {
		this.conjugue = conjugue;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Filho> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Filho> filhos) {
		this.filhos = filhos;
	}

	public List<RendaMensal> getRendas() {
		return rendas;
	}

	public void setRendas(List<RendaMensal> rendas) {
		this.rendas = rendas;
	}

	public Pessoa criaId() {
		setId(new ObjectId());
		return this;
	}
	
	public PessoaDto converterToDto(Pessoa pessoa) {
		
		PessoaDto dto = new PessoaDto();
		
		dto.setId(pessoa.getId().toString());
		dto.setNome(pessoa.getNome());
		dto.setNomePai(pessoa.getNomePai());
		dto.setNomeMae(pessoa.getNomeMae());
		dto.setDataNascimento(pessoa.getDataNascimento());
		dto.setNaturalidade(pessoa.getNaturalidade());
		dto.setProfissao(pessoa.getProfissao());
		dto.setCpf(pessoa.getCpf());
		dto.setRg(pessoa.getRg());
		dto.setSsp(pessoa.getSsp());
		dto.setTituloEleitor(pessoa.getTituloEleitor());
		dto.setEstadoCivil(pessoa.getEstadoCivil());
		dto.setRegistroComunhao(pessoa.getRegistroComunhao());
		
		
		ConjugueDto conjugueDto = new ConjugueDto();
		
		conjugueDto.setCpf(pessoa.getConjugue().getCpf());
		conjugueDto.setDataNascimento(pessoa.getConjugue().getDataNascimento());
		conjugueDto.setNome(pessoa.getConjugue().getNome());
		conjugueDto.setProfissao(pessoa.getConjugue().getProfissao());
		conjugueDto.setRg(pessoa.getConjugue().getRg());
		conjugueDto.setSsp(pessoa.getConjugue().getSsp());
		
		dto.setConjugue(conjugueDto);
		
		
		List<EnderecoDto> listaEndereco = new ArrayList<EnderecoDto>();
		
		pessoa.getEnderecos().forEach(e -> {
			
			EnderecoDto enderecoDto = new EnderecoDto();
			
			enderecoDto.setId(e.getId().toString());
			enderecoDto.setBairro(e.getBairro());
			enderecoDto.setCidade(e.getCidade());
			enderecoDto.setCep(e.getCep());
			enderecoDto.setDescricao(e.getDescricao());
			enderecoDto.setUf(e.getUf());
			
			listaEndereco.add(enderecoDto);
		});
		
		dto.setEnderecos(listaEndereco);
		
		List<TelefoneDto> listaTelefone = new ArrayList<TelefoneDto>();
		
		pessoa.getTelefones().forEach(t -> {
			TelefoneDto teldto = new TelefoneDto();
			
			teldto.setId(t.getId().toString());
			teldto.setDescricao(t.getDescricao());
			teldto.setNumero(t.getNumero());
			
			listaTelefone.add(teldto);
		});
		
		dto.setTelefones(listaTelefone);
		
		List<EmailDto> listaEmail = new ArrayList<EmailDto>();
		
		pessoa.getEmails().forEach(e ->{
			EmailDto email = new EmailDto();
			
			email.setId(e.getId().toString());
			email.setEndereco(e.getEndereco());
			
			listaEmail.add(email);
		});
		
		dto.setEmails(listaEmail);
		
		List<FilhoDto> listaFilho = new ArrayList<FilhoDto>();
		
		pessoa.getFilhos().forEach(f -> {
			FilhoDto filho = new FilhoDto();
			
			filho.setId(f.getId().toString());
			filho.setDataNascimento(f.getDataNascimento());
			filho.setNome(f.getNome());
			
			listaFilho.add(filho);
		});
		
		dto.setFilhos(listaFilho);
		
		List<RendaMensalDto> rendaDto = new ArrayList<RendaMensalDto>();
		
		pessoa.getRendas().forEach(r -> {
			
			RendaMensalDto renda = new RendaMensalDto();
			
			renda.setId(r.getId().toString());
			renda.setDescricao(r.getDescricao());
			renda.setValor(r.getValor());
			
			rendaDto.add(renda);
			
		});
		
		dto.setRendas(rendaDto);
		
		return dto;
	}

}
