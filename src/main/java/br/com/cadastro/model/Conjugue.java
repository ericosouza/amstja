package br.com.cadastro.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class Conjugue implements Serializable {
	
	private static final long serialVersionUID = 1105915913681355356L;
	
	ObjectId id;
	String nome;
	Date dataNascimento;
	String cpf;
	String rg;
	String ssp;
	String profissao;
	
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	

}
