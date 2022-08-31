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

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Food food;

	@Column(nullable = false)
	private float amount;

	@Embedded
	private NutritionalData nutritionalData;

	private LocalDateTime dateTimeRegistration = LocalDateTime.now();
	
	public MenuPerson() {
		super();
	}

	public MenuPerson(Food food, float amount, float calories, float protein, float lipids, float carbohydrate) {
		this.nutritionalData = new NutritionalData();
		this.nutritionalData.setCalories(calories);
		this.nutritionalData.setCarbohydrate(carbohydrate);
		this.nutritionalData.setLipids(lipids);
		this.nutritionalData.setProtein(protein);
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

	public NutritionalData getNutritionalData() {
		return nutritionalData;
	}

	public void setNutritionalData(NutritionalData nutritionalData) {
		this.nutritionalData = nutritionalData;
	}

	public LocalDateTime getDateTimeRegistration() {
		return dateTimeRegistration;
	}

	public void setDateTimeRegistration(LocalDateTime dateTimeRegistration) {
		this.dateTimeRegistration = dateTimeRegistration;
	}

}
