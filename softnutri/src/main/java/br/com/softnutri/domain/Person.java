package br.com.softnutri.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.enuns.Gender;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idPerson;

	@Column(name = "name", nullable = false)
	protected String name;

	@Column(name = "email", unique = true, nullable = false)
	protected String email;

	@Column(name = "cpf", nullable = false)
	protected String cpf;

	@Column(name = "birthDate", nullable = false)
	protected LocalDate birthDate;

	@Column(name = "address", nullable = false)
	protected String address;

	@Column(name = "gender", nullable = false, length = 1)
	protected Gender gender;

	@Builder.Default
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<Phone> phones = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<BodyHistory> bodyHistory = new ArrayList<>();

}
