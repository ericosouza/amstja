package br.com.cadastro.model;

import org.bson.types.ObjectId;

public class Telefone {
	
	ObjectId id;
	String descricao;
	String numero;
	
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Telefone criaId() {
		setId(new ObjectId());
		return this;
	}
	
	

}
