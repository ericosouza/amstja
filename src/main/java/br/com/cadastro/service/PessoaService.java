package br.com.cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.dto.PessoaDto;
import br.com.cadastro.model.Pessoa;
import br.com.cadastro.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;

	public void salvar(Pessoa pessoa) throws IllegalAccessException {
			repository.salvar(pessoa);

	}
	
	public List<PessoaDto> listarTodasPessoas() {

		List<Pessoa> resultados;

		resultados = repository.obterTodos();
		
		List<PessoaDto> response = new ArrayList<PessoaDto>();
		
		resultados.forEach(r -> {
			
			PessoaDto dto = r.converterToDto(r);
			response.add(dto);
			
		});

		return response;

	}

}
