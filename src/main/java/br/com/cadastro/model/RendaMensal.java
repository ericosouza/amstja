package br.com.cadastro.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class RendaMensal implements Serializable {
	

	private static final long serialVersionUID = -2601771768257980433L;
	
	ObjectId id;
	String descricao;
	Double valor;
	
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
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public RendaMensal criaId() {
		setId(new ObjectId());
		return this;
	}
	
	
}
