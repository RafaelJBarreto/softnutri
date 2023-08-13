package br.com.softnutri.dto;

import java.util.List;

import lombok.Data;

@Data
public class FoodGroupAssociationDTO {

	private Long idFoodGroup;
	private List<Long> idFood;
}
