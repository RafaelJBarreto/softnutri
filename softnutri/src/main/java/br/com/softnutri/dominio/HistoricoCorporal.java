package br.com.softnutri.dominio;

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
@Table(name = "historicoCorporal")
public class HistoricoCorporal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHistoricoCorporal;

	@Basic(optional = false)
	@Column(name = "data", nullable = false)
	private LocalDateTime data;

	@Basic(optional = false)
	@Column(name = "peso", nullable = false)
	private BigDecimal peso;
	
	@Basic(optional = false)
	@Column(name = "altura", nullable = false)
	protected BigDecimal altura;

	@Basic(optional = false)
	@Column(name = "imc", nullable = false)
	private BigDecimal imc;

	@Column(name = "cintura", nullable = true)
	private BigDecimal cintura;

	@Column(name = "quadril", nullable = true)
	private BigDecimal quadril;

	@Column(name = "rcq", nullable = true)
	private BigDecimal rcq;

	@Column(name = "cb", nullable = true)
	private BigDecimal cb;

	@Column(name = "dct", nullable = true)
	private BigDecimal dct;

	@Column(name = "amb", nullable = true)
	private BigDecimal amb;

	@ManyToOne
	private Pessoa pessoa;

	public Long getIdHistoricoCorporal() {
		return idHistoricoCorporal;
	}

	public void setIdHistoricoCorporal(Long idHistoricoCorporal) {
		this.idHistoricoCorporal = idHistoricoCorporal;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc(BigDecimal imc) {
		this.imc = imc;
	}

	public BigDecimal getCintura() {
		return cintura;
	}

	public void setCintura(BigDecimal cintura) {
		this.cintura = cintura;
	}

	public BigDecimal getQuadril() {
		return quadril;
	}

	public void setQuadril(BigDecimal quadril) {
		this.quadril = quadril;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
