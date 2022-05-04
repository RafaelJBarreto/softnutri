package br.com.softnutri.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softnutri.controller.form.PessoaForm;
import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dto.PessoaDto;
import br.com.softnutri.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDto> lista(String nome) {
		if (nome == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return PessoaDto.converter(pessoas);
		} else {
			List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
			return PessoaDto.converter(pessoas);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDto> cadastrar(@RequestBody PessoaForm form, UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = form.converter(pessoaRepository);
		pessoaRepository.save(pessoa);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(pessoa.getIdPessoa()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}

}
