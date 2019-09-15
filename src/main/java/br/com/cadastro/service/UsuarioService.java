package br.com.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.model.Usuario;
import br.com.cadastro.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> buscarPorEmail(String email) {
		return Optional.ofNullable(this.repository.buscarPorEmail(email));
	}

	public void salvar(Usuario usuario) throws IllegalAccessException {
		repository.salvar(usuario);

	}

	public List<Usuario> listar() {
		return this.repository.listar();
	}
}
