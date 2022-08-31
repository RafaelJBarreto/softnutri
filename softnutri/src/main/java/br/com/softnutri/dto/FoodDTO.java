package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Food;

public class FoodDTO {

	private Long idFood;
	private String description;
	private NutritionalDataDTO nutritionalData;

	public FoodDTO() {
	}

	public FoodDTO(Long idFood, String description, float calories, float protein, float lipids, float carbohydrate) {
		this.idFood = idFood;
		this.description = description;
		this.nutritionalData = new NutritionalDataDTO();
		this.nutritionalData.setCalories(calories);
		this.nutritionalData.setCarbohydrate(carbohydrate);
		this.nutritionalData.setLipids(lipids);
		this.nutritionalData.setProtein(protein);
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
	
	public static Food converterToDomain(FoodDTO foodDTO) {
		return new Food(foodDTO.getIdFood(), foodDTO.getDescription(), foodDTO.getNutritionalData().getCalories(), foodDTO.getNutritionalData().getProtein(), 
				foodDTO.getNutritionalData().getLipids(), foodDTO.getNutritionalData().getCarbohydrate());
	}


	public static List<FoodDTO> converter(List<Food> foods) {
		return foods.stream().map(FoodDTO::new).toList();
	}

}
