package br.com.softnutri.dto;

public class FoodDTO {

	private Long idFood;
	private String description;
	private NutritionalDataDTO nutritionalData;
	
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

	
	
}
