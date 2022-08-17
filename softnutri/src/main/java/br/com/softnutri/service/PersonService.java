package br.com.softnutri.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Person;
import br.com.softnutri.repository.PersonRepository;

@Service(value = "personService")
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public ResponseEntity<MessageResponse> delete(Long idPerson) {
		Optional<Person> person = this.personRepository.findById(idPerson);
		if (person.isPresent()) {
			personRepository.delete(person.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}

}
