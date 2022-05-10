package br.com.softnutri.domain;

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
@Table(name = "menuPerson")
public class MenuPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenuPerson;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Food food;

	@Column(nullable = false)
	private float amount;

	@Embedded
	private NutritionalData dadosNutricionais;

	private LocalDateTime dateTimeRegistration = LocalDateTime.now();

	public MenuPerson(Food food, float amount, float calorias, float proteina, float lipideos, float carboidrato) {
		this.dadosNutricionais = new NutritionalData(calorias, proteina, lipideos, carboidrato);
		this.food = food;
		this.amount = amount;
		this.dateTimeRegistration = LocalDateTime.now();
	}

	public Long getIdMenuPerson() {
		return idMenuPerson;
	}

	public void setIdMenuPerson(Long idMenuPerson) {
		this.idMenuPerson = idMenuPerson;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public NutritionalData getDadosNutricionais() {
		return dadosNutricionais;
	}

	public void setDadosNutricionais(NutritionalData dadosNutricionais) {
		this.dadosNutricionais = dadosNutricionais;
	}

	public LocalDateTime getDateTimeRegistration() {
		return dateTimeRegistration;
	}

	public void setDateTimeRegistration(LocalDateTime dateTimeRegistration) {
		this.dateTimeRegistration = dateTimeRegistration;
	}

}
