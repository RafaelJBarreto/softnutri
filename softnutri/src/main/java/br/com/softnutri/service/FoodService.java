package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.CompositionTable;
import br.com.softnutri.domain.Food;
import br.com.softnutri.domain.NutritionalData;
import br.com.softnutri.dto.FoodDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.repository.FoodRepository;

@Service
public class FoodService {
	
	private final FoodRepository foodRepository;
	
	@Autowired
	public FoodService(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	public void save(FoodDTO foodDTO){ 
		try {
			this.foodRepository.save(
					Food.builder().idFood(foodDTO.getIdFood()).description(foodDTO.getDescription()).descriptionPreparation(foodDTO.getDescriptionPreparation()).
					compositionTable(CompositionTable.builder().idCompositionTable(foodDTO.getCompositionTable().idCompositionTable()).build()).
					nutritionalData(NutritionalData.builder().calories(foodDTO.getNutritionalData().getCalories()).carbohydrate(foodDTO.getNutritionalData().getCarbohydrate()).
							lipids(foodDTO.getNutritionalData().getLipids()).protein(foodDTO.getNutritionalData().getProtein()).build()).build()
					
			);
		} catch (Exception e) {
			throw new SoftNutriException("FOOD.FOOD_SAVE_ERROR", e);
		}	
	}
	public List<FoodDTO> listAll(){ 
		try {	
			return FoodDTO.converter(this.foodRepository.findAll());
		} catch (Exception e) {
			throw new SoftNutriException("FOOD.FOOD_LIST_ERROR", e);
		}	
	}
	
	public void delete(Long id){
		try {
			final Optional<Food> food = this.foodRepository.findById(id);
			food.ifPresent(foodRepository::delete);
			
		} catch (Exception e) {
			throw new SoftNutriException("FOOD.FOOD_REMOVE_ERROR", e);
		}	
	}


}
