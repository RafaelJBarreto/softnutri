package br.com.softnutri.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.Person;
import br.com.softnutri.util.Criptografia;

public class PersonDTO {

	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String endereco;
	private BigDecimal altura;
	private List<PhoneDTO> telefones;

	public PersonDTO(Person pessoa) {
		this.nome = Criptografia.decode(pessoa.getName());
		this.email = Criptografia.decode(pessoa.getEmail());
		this.cpf = Criptografia.decode(pessoa.getCpf());
		this.dataNascimento = pessoa.getBirthDate();
		this.endereco = Criptografia.decode(pessoa.getAddress());
		this.telefones = new ArrayList<>();
		this.telefones.addAll(pessoa.getPhones().stream().map(PhoneDTO::new).toList());
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public static List<PersonDTO> converter(List<Person> pessoas) {
		return pessoas.stream().map(PersonDTO::new).toList();
	}

}
