package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dto.PessoaDto;
import br.com.softnutri.repository.PessoaRepository;

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

}
