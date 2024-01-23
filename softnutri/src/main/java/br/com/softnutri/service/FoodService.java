package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
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
	
	public ResponseEntity<MessageResponse> save(FoodDTO foodDTO) throws SoftNutriException { 
		try {
			this.foodRepository.save(
					
					Food.builder().idFood(foodDTO.getIdFood()).description(foodDTO.getDescription()).descriptionPreparation(foodDTO.getDescriptionPreparation()).
					compositionTable(CompositionTable.builder().idCompositionTable(foodDTO.getCompositionTable().idCompositionTable()).build()).
					nutritionalData(NutritionalData.builder().calories(foodDTO.getNutritionalData().getCalories()).carbohydrate(foodDTO.getNutritionalData().getCarbohydrate()).
							lipids(foodDTO.getNutritionalData().getLipids()).protein(foodDTO.getNutritionalData().getProtein()).build()).build()
					
			);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		} catch (Exception e) {
			throw new SoftNutriException("Error save Food ", e);
		}	
	}
	public ResponseEntity<List<FoodDTO>> listAll() throws SoftNutriException { 
		try {	
			return ResponseEntity.ok(FoodDTO.converter(this.foodRepository.findAll()));
		} catch (Exception e) {
			throw new SoftNutriException("Error list all Food ", e);
		}	
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) throws SoftNutriException {
		try {
			Optional<Food> food = this.foodRepository.findById(id);
			if (food.isPresent()) {
				foodRepository.delete(food.get());
				return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
			} else {
				return ResponseEntity.ok(new MessageResponse("FOOD.FOOD_REMOVE_ERROR"));
			}
			
		} catch (Exception e) {
			throw new SoftNutriException("Error delete Food ", e);
		}	
	}


}
