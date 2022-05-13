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

import br.com.softnutri.dto.PersonDTO;
import br.com.softnutri.service.PersonService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public List<PersonDTO> getPersons(String name) {
		return personService.getPersons(name);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PersonDTO> cadastrar(@RequestBody PersonDTO form, UriComponentsBuilder uriBuilder) {
		return null;
	}

}
