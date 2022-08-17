package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.service.PersonService;
import br.com.softnutri.service.UserService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientController {

	private PersonService personService;
	private UserService userService;

	@Autowired
	public PatientController(PersonService personService, UserService userService) {
		this.personService = personService;
		this.userService = userService;
	}

	@GetMapping("/")
	public List<UserDTO> getPersons(String name) {
		return userService.getUsers(name);
	}

	@GetMapping(value = "/get/{id}")
	public UserDTO getPerson(@PathVariable(value = "id") Long id) {
		return userService.getUser(id);
	}

	@PostMapping("/save")
	@Transactional
	public ResponseEntity<MessageResponse> cadastrar(@RequestBody UserDTO form) {
		return this.userService.save(UserDTO.converterToDomain(form));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) {
		return personService.delete(id);
	}

}