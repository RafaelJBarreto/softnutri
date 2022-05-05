package br.com.softnutri.dominio;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DadosNutricionais implements Serializable {

	private static final long serialVersionUID = 1L;

	private float calorias = 0;

	private float proteina = 0;

	private float lipideos = 0;

	private float carboidrato = 0;

	public DadosNutricionais(float calorias, float proteina, float lipideos, float carboidrato) {
		this.calorias = calorias;
		this.proteina = proteina;
		this.lipideos = lipideos;
		this.carboidrato = carboidrato;
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
