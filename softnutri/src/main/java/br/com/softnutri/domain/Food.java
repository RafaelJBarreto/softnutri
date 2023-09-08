package br.com.softnutri.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFood;

	@Column(name = "description", nullable = false, length = 150)
	private String description;
	
	@Column(name = "descriptionPreparation", length = 150)
	private String descriptionPreparation;

	@Embedded
	private NutritionalData nutritionalData;
	
	@JoinColumn(name = "idCompositionTable", referencedColumnName = "idCompositionTable", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private CompositionTable compositionTable;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "food", fetch = FetchType.LAZY)
	private List<MenuPerson> menuPerson = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "food", fetch = FetchType.EAGER)
	private List<FoodBunch> foodBunch;

	public Food() {
	}
	
	public Food(Long idFood, String description, String descriptionPreparation, float calories, float protein, float lipids, float carbohydrate) {
		this.idFood = idFood;
		this.description = description;
		this.descriptionPreparation = descriptionPreparation;
		this.nutritionalData = new NutritionalData();
		this.nutritionalData.setCalories(calories);
		this.nutritionalData.setCarbohydrate(carbohydrate);
		this.nutritionalData.setLipids(lipids);
		this.nutritionalData.setProtein(protein);
	}


	public Food(String description, String descriptionPreparation, float calories, float protein, float lipids, float carbohydrate) {
		this.description = description;
		this.descriptionPreparation = descriptionPreparation;
		this.nutritionalData = new NutritionalData();
		this.nutritionalData.setCalories(calories);
		this.nutritionalData.setCarbohydrate(carbohydrate);
		this.nutritionalData.setLipids(lipids);
		this.nutritionalData.setProtein(protein);
	}

}
