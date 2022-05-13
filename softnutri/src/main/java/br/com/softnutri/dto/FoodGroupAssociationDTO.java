package br.com.softnutri.dto;

import java.util.List;

public class FoodGroupAssociationDTO {

	private Long idFoodGroup;
	private List<Long> idFood;
	public Long getIdFoodGroup() {
		return idFoodGroup;
	}
	public void setIdFoodGroup(Long idFoodGroup) {
		this.idFoodGroup = idFoodGroup;
	}
	public List<Long> getIdFood() {
		return idFood;
	}
	public void setIdFood(List<Long> idFood) {
		this.idFood = idFood;
	}
	
	
}
