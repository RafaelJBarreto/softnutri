package br.com.softnutri.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "foodBunch")
public class FoodBunch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFoodBunch;

	@JoinColumn(name = "idFood", referencedColumnName = "idFood", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Food food;

	@JoinColumn(name = "idBunch", referencedColumnName = "idBunch", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Bunch bunch;

	public FoodBunch() {
	}

	public FoodBunch(Long idFoodBunch) {
		super();
		this.idFoodBunch = idFoodBunch;
	}

	public FoodBunch(Long idFoodBunch, Food food, Bunch bunch) {
		this.idFoodBunch = idFoodBunch;
		this.food = food;
		this.bunch = bunch;
	}


}
