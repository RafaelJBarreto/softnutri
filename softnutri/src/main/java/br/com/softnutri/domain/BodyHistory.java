package br.com.softnutri.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bodyHistory")
public class BodyHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBodyHistory;

	@Basic(optional = false)
	@Column(name = "data", nullable = false)
	private LocalDateTime data;

	@Basic(optional = false)
	@Column(name = "weight", nullable = false)
	private BigDecimal weight;

	@Basic(optional = false)
	@Column(name = "height", nullable = false)
	protected BigDecimal height;

	@Basic(optional = false)
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

	public Long getIdBodyHistory() {
		return idBodyHistory;
	}

	public void setIdBodyHistory(Long idBodyHistory) {
		this.idBodyHistory = idBodyHistory;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc(BigDecimal imc) {
		this.imc = imc;
	}

	public BigDecimal getWaist() {
		return waist;
	}

	public void setWaist(BigDecimal waist) {
		this.waist = waist;
	}

	public BigDecimal getHip() {
		return hip;
	}

	public void setHip(BigDecimal hip) {
		this.hip = hip;
	}

	public BigDecimal getRcq() {
		return rcq;
	}

	public void setRcq(BigDecimal rcq) {
		this.rcq = rcq;
	}

	public BigDecimal getCb() {
		return cb;
	}

	public void setCb(BigDecimal cb) {
		this.cb = cb;
	}

	public BigDecimal getDct() {
		return dct;
	}

	public void setDct(BigDecimal dct) {
		this.dct = dct;
	}

	public BigDecimal getAmb() {
		return amb;
	}

	public void setAmb(BigDecimal amb) {
		this.amb = amb;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
