package br.com.cadastro.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = -3943598349334945992L;
	
	ObjectId id;
	String descricao;
	String bairro;
	String cidade;
	String cep;
	String uf;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Endereco criaId() {
		setId(new ObjectId());
		return this;
	}
	
	

}
