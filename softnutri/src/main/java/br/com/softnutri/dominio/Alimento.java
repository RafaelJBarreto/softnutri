package br.com.softnutri.dominio;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimento")
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlimento;

	@Basic(optional = false)
	@Column(name = "descricao", nullable = false, length = 150)
	private String descricao;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private GrupoAlimento grupoAlimento;

	private float calorias = 0;

	private float proteina = 0;

	private float lipideos = 0;

	private float carboidrato = 0;

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoAlimento getGrupoAlimento() {
		return grupoAlimento;
	}

	public void setGrupoAlimento(GrupoAlimento grupoAlimento) {
		this.grupoAlimento = grupoAlimento;
	}

	public float getCalorias() {
		return calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public float getProteina() {
		return proteina;
	}

	public void setProteina(float proteina) {
		this.proteina = proteina;
	}

	public float getLipideos() {
		return lipideos;
	}

	public void setLipideos(float lipideos) {
		this.lipideos = lipideos;
	}

	public float getCarboidrato() {
		return carboidrato;
	}

	public void setCarboidrato(float carboidrato) {
		this.carboidrato = carboidrato;
	}

}
