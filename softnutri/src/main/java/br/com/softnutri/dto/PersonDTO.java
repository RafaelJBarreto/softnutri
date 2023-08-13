package br.com.softnutri.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.softnutri.enuns.Gender;
import lombok.Data;

@Data
public class PersonDTO {

	protected Long idPerson;
	protected String name;
	protected String email;
	protected String cpf;
	protected LocalDate birthDate;
	protected String address;
	protected List<PhoneDTO> phones;
	protected Gender gender;
	
	public PersonDTO() {
	}
	
	public PersonDTO(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	public PersonDTO(Long idPerson, String name, String email, String cpf, LocalDate birthDate, String address,
			Gender gender) {
		this.idPerson = idPerson;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.address = address;
		this.gender = gender;
	}
}
