package br.com.softnutri.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFood;

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 150)
	private String description;

	@Embedded
	private NutritionalData nutritionalData;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "food", fetch = FetchType.LAZY)
	private List<MenuPerson> menuPerson = new ArrayList<>();

	public Food() {
	}

	public Food(String description, float calories, float protein, float lipids, float carbohydrate) {
		this.description = description;
		this.nutritionalData = new NutritionalData();
		this.nutritionalData.setCalories(calories);
		this.nutritionalData.setCarbohydrate(carbohydrate);
		this.nutritionalData.setLipids(lipids);
		this.nutritionalData.setProtein(protein);
	}

	public Long getIdFood() {
		return idFood;
	}

	public void setIdFood(Long idFood) {
		this.idFood = idFood;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NutritionalData getNutritionalData() {
		return nutritionalData;
	}

	public void setNutritionalData(NutritionalData nutritionalData) {
		this.nutritionalData = nutritionalData;
	}

	public List<MenuPerson> getMenuPerson() {
		return menuPerson;
	}

	public void setMenuPerson(List<MenuPerson> menuPerson) {
		this.menuPerson = menuPerson;
	}

}
