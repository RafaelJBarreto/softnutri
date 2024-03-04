package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.CompositionTable;
import br.com.softnutri.domain.Food;
import br.com.softnutri.domain.NutritionalData;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.records.CompositionTableDTO;
import br.com.softnutri.records.FoodDTO;
import br.com.softnutri.records.NutritionalDataDTO;
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
					Food.builder().idFood(foodDTO.idFood()).description(foodDTO.description()).descriptionPreparation(foodDTO.descriptionPreparation()).
					compositionTable(CompositionTable.builder().idCompositionTable(foodDTO.compositionTable().idCompositionTable()).build()).
					nutritionalData(NutritionalData.builder().calories(foodDTO.nutritionalData().calories()).carbohydrate(foodDTO.nutritionalData().carbohydrate()).
							lipids(foodDTO.nutritionalData().lipids()).protein(foodDTO.nutritionalData().protein()).build()).build()
					
			);
		} catch (Exception e) {
			throw new SoftNutriException("FOOD.FOOD_SAVE_ERROR", e);
		}	
	}
	public List<FoodDTO> listAll(){ 
		try {	
			return this.foodRepository.findAll().stream().map( o -> new FoodDTO(o.getIdFood(), o.getDescription(), o.getDescriptionPreparation(), 
												new CompositionTableDTO(o.getCompositionTable().getIdCompositionTable(), o.getCompositionTable().getName(), o.getCompositionTable().getDescription()), 
												new NutritionalDataDTO(o.getNutritionalData().getCalories(), o.getNutritionalData().getProtein(), o.getNutritionalData().getLipids(), o.getNutritionalData().getCarbohydrate()))).toList();
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
