package br.com.softnutri.records;

import java.util.List;

public record FoodGroupAssociationDTO(Long idFoodGroup, List<Long> idFood) {

}
