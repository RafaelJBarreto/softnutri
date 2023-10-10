package br.com.softnutri.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.softnutri.enuns.UserType;
import jakarta.persistence.Basic;
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
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "user")
public class User extends Person {

	@Basic(optional = true)
	@Column(name = "password", nullable = true)
	private String password;

	@Basic(optional = true)
	@Column(name = "language", nullable = true)
	private String language;

	@Basic(optional = false)
	@Column(name = "userType", nullable = false, length = 30)
	private UserType userType;

	@Basic(optional = false)
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
