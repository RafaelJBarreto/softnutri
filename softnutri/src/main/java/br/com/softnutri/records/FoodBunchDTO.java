package br.com.softnutri.records;

import java.util.List;

public record FoodBunchDTO(Long idFoodBunch, BunchDTO bunch, FoodDTO food, List<FoodDTO> foods) {

}
