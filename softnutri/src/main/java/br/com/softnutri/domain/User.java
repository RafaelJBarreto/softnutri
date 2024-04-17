package br.com.softnutri.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.softnutri.enuns.UserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "user")
public class User extends Person {

	@Column(name = "password", nullable = true)
	private String password;

	@Column(name = "language", nullable = true)
	private String language;

	@Column(name = "userType", nullable = false, length = 30)
	private UserType userType;

	@Column(name = "dateRegister", nullable = false)
	private LocalDateTime dateRegister;

	private String crn;
	
	@Builder.Default
	@OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarProfessional = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarPatient = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarReceptionist = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<PersonPaper> paper;
}
