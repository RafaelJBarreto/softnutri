package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.util.FoodBunchReturn;
import lombok.Data;

@Data
public class FoodBunchDTO {

	private Long idFoodBunch;
	private BunchDTO bunch;
	private FoodDTO food;
	private List<FoodDTO> foods;

	public FoodBunchDTO() {
	}

	public FoodBunchDTO(FoodBunchReturn foodBunch) {
		this.idFoodBunch = foodBunch.getIdFoodBunch();
		this.bunch = new BunchDTO(foodBunch.getIdBunch(), foodBunch.getDescription());
		this.food = new FoodDTO(foodBunch.getIdFood(), foodBunch.getDescriptionFood(), foodBunch.getCalories(),
				foodBunch.getProtein(), foodBunch.getLipids(), foodBunch.getCarbohydrate());
	}


	public static List<FoodBunchDTO> converter(List<FoodBunchReturn> foods) {
		return foods.stream().map(FoodBunchDTO::new).toList();
	}
}
