package br.com.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.model.Email;
import br.com.cadastro.model.Endereco;
import br.com.cadastro.model.Filho;
import br.com.cadastro.model.Pessoa;
import br.com.cadastro.model.RendaMensal;
import br.com.cadastro.model.Telefone;
import br.com.cadastro.repository.PessoaRepository;
import br.com.cadastro.service.PessoaService;

@RestController
@RequestMapping("pessoa")
public class PessoaController {
	
	@Autowired
	PessoaRepository repository;

	@Autowired
	private PessoaService service;

	@GetMapping("/listar")
	public ResponseEntity<?> listarCollection() {
		return ResponseEntity.ok(service.listarTodasPessoas());
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) {
		try {
			this.service.salvar(pessoa);
			return ResponseEntity.ok("Registro salvo com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PostMapping("/adicionarEndereco")
	public ResponseEntity<?> salvarEndereco(@RequestParam String idPessoa, 
			@RequestBody Endereco endereco){
		
		Pessoa pessoa = this.repository.obterPessoaPorID(idPessoa);
		this.repository.salvar(this.repository.adicionaEndereco(pessoa, endereco));		
		return ResponseEntity.ok("Endereço cadastrado com sucesso!");
	}
	
	@PostMapping("/adicionarTelefone")
	public ResponseEntity<?> salvarTelefone(@RequestParam String idPessoa, 
			@RequestBody Telefone telefone){
		
		Pessoa pessoa = this.repository.obterPessoaPorID(idPessoa);
		this.repository.salvar(this.repository.adicionaTelefone(pessoa, telefone));		
		return ResponseEntity.ok("Telefone cadastrado com sucesso!");
	}
	
	@PostMapping("/adicionarFilho")
	public ResponseEntity<?> salvarFilho(@RequestParam String idPessoa, 
			@RequestBody Filho filho){
		
		Pessoa pessoa = this.repository.obterPessoaPorID(idPessoa);
		this.repository.salvar(this.repository.adicionaFilho(pessoa, filho));		
		return ResponseEntity.ok("Filho cadastrado com sucesso!");
	}
	
	@PostMapping("/adicionarEmail")
	public ResponseEntity<?> salvarEmail(@RequestParam String idPessoa, 
			@RequestBody Email email){
		
		Pessoa pessoa = this.repository.obterPessoaPorID(idPessoa);
		this.repository.salvar(this.repository.adicionaEmail(pessoa, email));		
		return ResponseEntity.ok("E-mail cadastrado com sucesso!");
	}
	
	@PostMapping("/adicionarRenda")
	public ResponseEntity<?> salvarRenda(@RequestParam String idPessoa, 
			@RequestBody RendaMensal renda){
		
		Pessoa pessoa = this.repository.obterPessoaPorID(idPessoa);
		this.repository.salvar(this.repository.adicionaRenda(pessoa, renda));		
		return ResponseEntity.ok("E-mail cadastrado com sucesso!");
	}

}
