package br.com.cadastro.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Email implements Serializable {

	private static final long serialVersionUID = 1603718420963662301L;
	
	ObjectId id;
	String endereco;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Email criaId() {
		setId(new ObjectId());
		return this;
	}
	
	

}
