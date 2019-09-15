package br.com.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.model.Usuario;
import br.com.cadastro.service.UsuarioService;
import br.com.cadastro.utils.PasswordUtils;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
		String senha = usuario.getSenha();
		usuario.setSenha(PasswordUtils.gerarBCrypt(senha));
		
		try {
			this.service.salvar(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarCollection() {
		return ResponseEntity.ok(service.listar());
	}

}
