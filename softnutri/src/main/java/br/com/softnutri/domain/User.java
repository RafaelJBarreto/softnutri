package br.com.softnutri.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import br.com.softnutri.enuns.UserType;
import br.com.softnutri.util.Criptografia;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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
	private LocalDateTime dateRegister = LocalDateTime.now();

	private String crn;
	
	@OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarProfessional;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarPatient;
	
	@OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Calendar> calendarReceptionist;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<PersonPaper> paper;
	
	public User() {
	}

	public User(Long idPerson) {
		super(idPerson);
	}

	public void setPassword(String password) {
		this.password = password != null ? Criptografia.encoderSecurity(password) : password;
	}
}
