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

import br.com.softnutri.domain.Person;
import br.com.softnutri.dto.PersonDTO;
import br.com.softnutri.repository.PersonRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {
	
	@Autowired
	private PersonRepository pessoaRepository;

	@GetMapping
	public List<PersonDTO> lista(String nome) {
		if (nome == null) {
			List<Person> pessoas = pessoaRepository.findAll();
			return PersonDTO.converter(pessoas);
		} else {
			List<Person> pessoas = pessoaRepository.findByName(nome);
			return PersonDTO.converter(pessoas);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PersonDTO> cadastrar(@RequestBody PersonDTO form, UriComponentsBuilder uriBuilder) {
		return null;
	}

}
