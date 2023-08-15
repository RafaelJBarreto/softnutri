package br.com.softnutri.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "personPaper")
@EqualsAndHashCode
public class PersonPaper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonPaper;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Paper paper;

	@Column(name = "access", nullable = false)
	private int get;

	@Column(name = "send", nullable = false)
	protected int post;

	@Column(name = "put", nullable = false)
	private int put;

	@Column(name = "remove", nullable = true)
	private int delete;
	
	public PersonPaper() {
	}
	

	public PersonPaper(Long idPersonPaper, User user, Paper paper, int get, int post, int put, int delete) {
		this.idPersonPaper = idPersonPaper;
		this.user = user;
		this.paper = paper;
		this.get = get;
		this.post = post;
		this.put = put;
		this.delete = delete;
	}



	
	
}
