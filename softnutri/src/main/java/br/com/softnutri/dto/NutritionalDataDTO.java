package br.com.softnutri.dto;

import br.com.softnutri.domain.NutritionalData;

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

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getLipids() {
		return lipids;
	}

	public void setLipids(float lipids) {
		this.lipids = lipids;
	}

	public float getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

}
