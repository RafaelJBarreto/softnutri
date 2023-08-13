package br.com.softnutri.dto;

import br.com.softnutri.domain.NutritionalData;
import lombok.Data;

@Data
public class NutritionalDataDTO {

	private float calories;
	private float protein = 0;
	private float lipids = 0;
	private float carbohydrate = 0;

	public NutritionalDataDTO() {
	}

	public NutritionalDataDTO(NutritionalData nutritionalData) {
		this.calories = nutritionalData.getCalories();
		this.protein = nutritionalData.getProtein();
		this.lipids = nutritionalData.getLipids();
		this.carbohydrate = nutritionalData.getCarbohydrate();
	}
}
