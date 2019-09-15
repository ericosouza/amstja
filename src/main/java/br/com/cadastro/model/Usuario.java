package br.com.cadastro.model;

import java.io.Serializable;

import org.bson.types.ObjectId;


public class Usuario implements Serializable {

	private static final long serialVersionUID = -5688527640426236177L;
	
	private ObjectId id;
	private String nome;
	private String email;
	private String senha;
	private String perfil;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

	public Usuario criaId() {
		setId(new ObjectId());
		return this;
	}
	
}
