package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.FoodGroup;

public class FoodGroupDTO {

	private Long idFoodGroup;
	private String description;

	public FoodGroupDTO() {
	}

	public FoodGroupDTO(FoodGroup foodGroup) {
		this.idFoodGroup = foodGroup.getIdFoodGroup();
		this.description = foodGroup.getDescription();
	}

	public Long getIdFoodGroup() {
		return idFoodGroup;
	}

	public void setIdFoodGroup(Long idFoodGroup) {
		this.idFoodGroup = idFoodGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<FoodGroupDTO> converter(List<FoodGroup> foodGroups) {
		return foodGroups.stream().map(FoodGroupDTO::new).toList();
	}
}
