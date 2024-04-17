package br.com.softnutri.records;

public record FoodDTO(Long idFood, String description, String descriptionPreparation, CompositionTableDTO compositionTable, NutritionalDataDTO nutritionalData) {

	public FoodDTO{
		if(descriptionPreparation == null) {
			descriptionPreparation = "GLOBAL.WITHOUTGROUP";
		}
	}
	
}
