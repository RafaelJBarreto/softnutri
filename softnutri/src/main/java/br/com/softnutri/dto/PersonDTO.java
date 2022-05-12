package br.com.softnutri.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.Person;
import br.com.softnutri.util.Criptografia;

public class PersonDTO {

	private String name;
	private String email;
	private String cpf;
	private LocalDate birthDate;
	private String address;
	private List<PhoneDTO> phones;

	public PersonDTO(Person pessoa) {
		this.name = Criptografia.decode(pessoa.getName());
		this.email = Criptografia.decode(pessoa.getEmail());
		this.cpf = Criptografia.decode(pessoa.getCpf());
		this.birthDate = pessoa.getBirthDate();
		this.address = Criptografia.decode(pessoa.getAddress());
		this.phones = new ArrayList<>();
		this.phones.addAll(pessoa.getPhones().stream().map(PhoneDTO::new).toList());
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

	public static List<PersonDTO> converter(List<Person> pessoas) {
		return pessoas.stream().map(PersonDTO::new).toList();
	}
}
