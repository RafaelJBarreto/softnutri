package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Food;

public class FoodDTO {

	private Long idFood;
	private String description;
	private NutritionalDataDTO nutritionalData;

	public FoodDTO() {
	}

	public FoodDTO(Food food) {
		this.idFood = food.getIdFood();
		this.description = food.getDescription();
		this.nutritionalData = new NutritionalDataDTO(food.getNutritionalData());
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

	public NutritionalDataDTO getNutritionalData() {
		return nutritionalData;
	}

	public void setNutritionalData(NutritionalDataDTO nutritionalData) {
		this.nutritionalData = nutritionalData;
	}

	public static List<FoodDTO> converter(List<Food> foods) {
		return foods.stream().map(FoodDTO::new).toList();
	}

}
