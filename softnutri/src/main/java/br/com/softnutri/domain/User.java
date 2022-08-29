package br.com.softnutri.domain;

import java.time.LocalDateTime;
import java.util.Set;

import br.com.softnutri.enuns.UserType;
import br.com.softnutri.util.Criptografia;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "personPaper", joinColumns = { @JoinColumn(name = "idPerson") }, inverseJoinColumns = {
			@JoinColumn(name = "idPaper") })
	private Set<Paper> paper;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password != null ? Criptografia.encoderSecurity(password) : password;
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

	public LocalDateTime getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDateTime dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Set<Paper> getPaper() {
		return paper;
	}

	public void setPaper(Set<Paper> paper) {
		this.paper = paper;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}
}
