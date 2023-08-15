package br.com.softnutri.interfaces;

public interface FoodBunchReturn {

	Long getIdFoodBunch();
	Long getIdBunch();
	String getDescription();
	Long getIdFood();
	String getDescriptionFood();
	float getCalories();
	float getProtein();
	float getLipids();
	float getCarbohydrate();
}
