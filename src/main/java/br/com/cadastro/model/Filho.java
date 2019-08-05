package br.com.cadastro.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class Filho implements Serializable {

	private static final long serialVersionUID = 4093715609620452371L;
	
	ObjectId id;
	String nome;
	Date dataNascimento;
	
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
	
	public Filho criaId() {
		setId(new ObjectId());
		return this;
	}
	
	

}
