package br.com.softnutri.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
