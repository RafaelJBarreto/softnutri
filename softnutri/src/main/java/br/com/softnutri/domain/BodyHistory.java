package br.com.softnutri.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bodyHistory")
public class BodyHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBodyHistory;

	@Column(name = "data", nullable = false)
	private LocalDateTime data;

	@Column(name = "weight", nullable = false)
	private BigDecimal weight;

	@Column(name = "height", nullable = false)
	protected BigDecimal height;

	@Column(name = "imc", nullable = false)
	private BigDecimal imc;

	@Column(name = "waist", nullable = true)
	private BigDecimal waist;

	@Column(name = "hip", nullable = true)
	private BigDecimal hip;

	@Column(name = "rcq", nullable = true)
	private BigDecimal rcq;

	@Column(name = "cb", nullable = true)
	private BigDecimal cb;

	@Column(name = "dct", nullable = true)
	private BigDecimal dct;

	@Column(name = "amb", nullable = true)
	private BigDecimal amb;

	@ManyToOne
	private Person person;

}
