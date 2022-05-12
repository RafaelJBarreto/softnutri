package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Person;
import br.com.softnutri.dto.PersonDTO;
import br.com.softnutri.repository.PersonRepository;

@Service(value = "personService")
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<PersonDTO> getPersons(String name) {
		if (name == null) {
			List<Person> pessoas = personRepository.findAll();
			return PersonDTO.converter(pessoas);
		} else {
			List<Person> pessoas = personRepository.findByName(name);
			return PersonDTO.converter(pessoas);
		}

	}

}
