package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import br.com.softnutri.dto.prototype.UserPrototype;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.service.PersonService;
import br.com.softnutri.service.UserService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/professional")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfessionalController {

	private PersonService personService;
	private UserService userService;

	@Autowired
	public ProfessionalController(PersonService personService, UserService userService) {
		this.personService = personService;
		this.userService = userService;
	}

	@GetMapping("/")
	public List<UserDTO> getProfessional() throws SoftNutriException {
		return userService.getProfessional();
	}

	@GetMapping(value = "/get/{id}")
	public UserDTO getProfessional(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return userService.getUser(id);
	}

	@PostMapping("/save")
	@Transactional
	public ResponseEntity<MessageResponse> cadastrar(@RequestBody UserDTO form) throws SoftNutriException {
		return this.userService.save(UserPrototype.getUser(form, userService));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return personService.delete(id);
	}
	
	@GetMapping("/nutritionist")
	@Cacheable("nutritionist")
	public List<UserDTO> getNutritionist() throws SoftNutriException {
		return userService.getNutritionist();
	}

}
