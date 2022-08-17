package br.com.softnutri.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.softnutri.enuns.Gender;

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

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
