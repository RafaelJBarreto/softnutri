package br.com.softnutri.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cardapioPessoa")
public class CardapioPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCardapioPessoa;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Alimento alimento;

	@Column(nullable = false)
	private float quantidade;

	@Embedded
	private DadosNutricionais dadosNutricionais;

	private LocalDateTime dataHora = LocalDateTime.now();

	public CardapioPessoa(Alimento alimento, float quantidade, float calorias, float proteina, float lipideos,
			float carboidrato) {
		this.dadosNutricionais = new DadosNutricionais(calorias, proteina, lipideos, carboidrato);
		this.alimento = alimento;
		this.quantidade = quantidade;
		this.dataHora = LocalDateTime.now();
	}

	public Long getIdCardapioPessoa() {
		return idCardapioPessoa;
	}

	public void setIdCardapioPessoa(Long idCardapioPessoa) {
		this.idCardapioPessoa = idCardapioPessoa;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public DadosNutricionais getDadosNutricionais() {
		return dadosNutricionais;
	}

	public void setDadosNutricionais(DadosNutricionais dadosNutricionais) {
		this.dadosNutricionais = dadosNutricionais;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

}
