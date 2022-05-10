package br.com.softnutri.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class NutritionalData implements Serializable {

	private static final long serialVersionUID = 1L;

	private float calories = 0;

	private float protein = 0;

	private float lipids = 0;

	private float carbohydrate = 0;

	public NutritionalData(float calories, float protein, float lipids, float carbohydrate) {
		this.calories = calories;
		this.protein = protein;
		this.lipids = lipids;
		this.carbohydrate = carbohydrate;
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
