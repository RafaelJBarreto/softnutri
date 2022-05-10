package br.com.softnutri.domain;

import java.time.LocalDate;

import br.com.softnutri.util.Criptografia;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Person {

	@Basic(optional = false)
	@Column(name = "password", nullable = false)
	private String password;

	@Basic(optional = false)
	@Column(name = "language", nullable = false)
	private String language;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "userType", nullable = false, length = 15)
	private UserType userType;

	@Basic(optional = false)
	@Column(name = "dateRegister", nullable = false)
	private LocalDate dateRegister = LocalDate.now();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =  Criptografia.encoderSecurity(password);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public LocalDate getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDate dateRegister) {
		this.dateRegister = dateRegister;
	}

}
