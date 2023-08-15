package br.com.softnutri.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.enuns.Gender;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Person {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idPerson;

	@Basic(optional = false)
	@Column(name = "name", nullable = false)
	protected String name;

	@Basic(optional = false)
	@Column(name = "email", unique = true, nullable = false)
	protected String email;

	@Basic(optional = false)
	@Column(name = "cpf", nullable = false)
	protected String cpf;

	@Basic(optional = false)
	@Column(name = "birthDate", nullable = false)
	protected LocalDate birthDate;

	@Basic(optional = false)
	@Column(name = "address", nullable = false)
	protected String address;

	@Basic(optional = false)
	@Column(name = "gender", nullable = false, length = 1)
	protected Gender gender;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<Phone> phones = new ArrayList<>();

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<BodyHistory> bodyHistory = new ArrayList<>();
	
	public Person() {
	}

	public Person(Long idPerson) {
		this.idPerson = idPerson;
	}
}
