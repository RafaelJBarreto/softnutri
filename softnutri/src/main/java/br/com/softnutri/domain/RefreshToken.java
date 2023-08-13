package br.com.softnutri.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "refreshToken")
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson", unique = true)
	private Person person;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiryDate;

	public RefreshToken() {
	}

	public RefreshToken(long id, Person person, String token, Instant expiryDate) {
		this.id = id;
		this.person = person;
		this.token = token;
		this.expiryDate = expiryDate;
	}

}
