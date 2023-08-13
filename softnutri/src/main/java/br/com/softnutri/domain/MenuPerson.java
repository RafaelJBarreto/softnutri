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
import lombok.Data;

@Data
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

}
