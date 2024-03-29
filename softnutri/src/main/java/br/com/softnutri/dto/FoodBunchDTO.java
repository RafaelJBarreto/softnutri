package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.interfaces.FoodBunchReturn;
import br.com.softnutri.record.BunchRecord;
import lombok.Data;

@Data
public class FoodBunchDTO {

	private Long idFoodBunch;
	private BunchRecord bunch;
	private FoodDTO food;
	private List<FoodDTO> foods;

	public FoodBunchDTO() {
	}

	public FoodBunchDTO(FoodBunchReturn foodBunch) {
		this.idFoodBunch = foodBunch.getIdFoodBunch();
		this.bunch = new BunchRecord(foodBunch.getIdBunch(), foodBunch.getDescription());
		this.food = new FoodDTO(foodBunch);
	}


	public static List<FoodBunchDTO> converter(List<FoodBunchReturn> foods) {
		return foods.stream().map(FoodBunchDTO::new).toList();
	}
}
