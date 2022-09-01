package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.util.FoodBunchReturn;

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

	public Long getIdFoodBunch() {
		return idFoodBunch;
	}

	public void setIdFoodBunch(Long idFoodBunch) {
		this.idFoodBunch = idFoodBunch;
	}

	public BunchDTO getBunch() {
		return bunch;
	}

	public void setBunch(BunchDTO bunch) {
		this.bunch = bunch;
	}

	public FoodDTO getFood() {
		return food;
	}

	public void setFood(FoodDTO food) {
		this.food = food;
	}

	public List<FoodDTO> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodDTO> foods) {
		this.foods = foods;
	}

	public static List<FoodBunchDTO> converter(List<FoodBunchReturn> foods) {
		return foods.stream().map(FoodBunchDTO::new).toList();
	}
}
