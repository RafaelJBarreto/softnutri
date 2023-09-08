package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Food;
import br.com.softnutri.interfaces.FoodBunchReturn;
import lombok.Data;

@Data
public class FoodDTO {

	private Long idFood;
	private String description;
	private String descriptionPreparation;
	private CompositionTableDTO compositionTable;
	private NutritionalDataDTO nutritionalData;

	public FoodDTO() {
	}

	public FoodDTO(FoodBunchReturn foodBunch) {
		this.idFood = foodBunch.getIdFood();
		this.description = foodBunch.getDescriptionFood();
		this.descriptionPreparation = foodBunch.getDescriptionPreparation();
		this.nutritionalData = new NutritionalDataDTO();
		this.nutritionalData.setCalories(foodBunch.getCalories());
		this.nutritionalData.setCarbohydrate(foodBunch.getCarbohydrate());
		this.nutritionalData.setLipids(foodBunch.getLipids());
		this.nutritionalData.setProtein(foodBunch.getProtein());
		this.compositionTable = new CompositionTableDTO (foodBunch.getIdCompositionTable(), foodBunch.getNameTable(), foodBunch.getDescriptionTable());
	}
	
	public FoodDTO(Food food) {
		this.idFood = food.getIdFood();
		this.description = food.getDescription();
		this.descriptionPreparation = food.getDescriptionPreparation();
		this.nutritionalData = new NutritionalDataDTO(food.getNutritionalData());
	}

	
	public static Food converterToDomain(FoodDTO foodDTO) {
		return new Food(foodDTO.getIdFood(), foodDTO.getDescription(), foodDTO.getDescriptionPreparation(), foodDTO.getNutritionalData().getCalories(), foodDTO.getNutritionalData().getProtein(), 
				foodDTO.getNutritionalData().getLipids(), foodDTO.getNutritionalData().getCarbohydrate());
	}


	public static List<FoodDTO> converter(List<Food> foods) {
		return foods.stream().map(FoodDTO::new).toList();
	}

}
