package br.com.cadastro.dto;

import java.util.Date;
import java.util.List;

public class PessoaDto {
	
	String id;
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

	ConjugueDto conjugue;

	List<EnderecoDto> enderecos;
	List<TelefoneDto> telefones;
	List<EmailDto> emails;
	List<FilhoDto> filhos;
	List<RendaMensalDto> rendas;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	public ConjugueDto getConjugue() {
		return conjugue;
	}
	public void setConjugue(ConjugueDto conjugue) {
		this.conjugue = conjugue;
	}
	public List<EnderecoDto> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoDto> enderecos) {
		this.enderecos = enderecos;
	}
	public List<TelefoneDto> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<TelefoneDto> telefones) {
		this.telefones = telefones;
	}
	public List<EmailDto> getEmails() {
		return emails;
	}
	public void setEmails(List<EmailDto> emails) {
		this.emails = emails;
	}
	public List<FilhoDto> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<FilhoDto> filhos) {
		this.filhos = filhos;
	}
	public List<RendaMensalDto> getRendas() {
		return rendas;
	}
	public void setRendas(List<RendaMensalDto> rendas) {
		this.rendas = rendas;
	}
	
	

}
