package br.com.softnutri.interfaces;

public interface FoodBunchReturn {

	Long getIdFoodBunch();
	Long getIdBunch();
	String getDescription();
	Long getIdFood();
	String getDescriptionFood();
	String getDescriptionPreparation();
	float getCalories();
	float getProtein();
	float getLipids();
	float getCarbohydrate();
	long getIdCompositionTable();
	String getNameTable();
	String getDescriptionTable();
}
