package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dto.PessoaDTO;
import br.com.softnutri.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDTO> lista(String nome) {
		if (nome == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return PessoaDTO.converter(pessoas);
		} else {
			List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
			return PessoaDTO.converter(pessoas);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDTO> cadastrar(@RequestBody PessoaDTO form, UriComponentsBuilder uriBuilder) {
		return null;
	}

}
