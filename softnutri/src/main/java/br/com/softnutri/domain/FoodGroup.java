package br.com.softnutri.domain;

import java.util.Set;

import br.com.softnutri.dto.FoodGroupDTO;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "foodGroup")
public class FoodGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFoodGroup;

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "food_group_food", joinColumns = { @JoinColumn(name = "idFoodGroup") }, inverseJoinColumns = {
			@JoinColumn(name = "idFood") })
	private Set<Food> food;

	public FoodGroup() {
		super();
	}

	public FoodGroup(FoodGroupDTO foodGroupDTO) {
		this.idFoodGroup = foodGroupDTO.getIdFoodGroup();
		this.description = foodGroupDTO.getDescription();
	}

	public Set<Food> getFood() {
		return food;
	}

	public Long getIdFoodGroup() {
		return idFoodGroup;
	}

	public void setIdFoodGroup(Long idFoodGroup) {
		this.idFoodGroup = idFoodGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFood(Set<Food> food) {
		this.food = food;
	}

}
