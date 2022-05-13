package br.com.softnutri.dto;

public class NutritionalDataDTO {

	private float calories;
	private float protein = 0;
	private float lipids = 0;
	private float carbohydrate = 0;

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
