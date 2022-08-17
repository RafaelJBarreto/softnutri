package br.com.softnutri.domain;

import java.time.LocalDate;

import br.com.softnutri.enuns.UserType;
import br.com.softnutri.util.Criptografia;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
	private LocalDate dateRegister = LocalDate.now();
	
	public User() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =  password != null ? Criptografia.encoderSecurity(password) : password;
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
