package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Bunch;
import br.com.softnutri.domain.Food;
import br.com.softnutri.domain.FoodBunch;
import br.com.softnutri.dto.FoodBunchDTO;
import br.com.softnutri.dto.FoodDTO;
import br.com.softnutri.repository.FoodBunchRepository;

@Service
public class FoodBunchService {
	
	private final FoodBunchRepository foodBunchRepository;

	@Autowired
	public FoodBunchService(FoodBunchRepository foodBunchRepository) {
		this.foodBunchRepository = foodBunchRepository;
	}
	
	public ResponseEntity<MessageResponse> save(FoodBunchDTO foodBunchDTO) { 
		List<FoodBunch> getAllFoodBunch = this.foodBunchRepository.findAll();
		Bunch bunch = Bunch.builder().idBunch(foodBunchDTO.getBunch().getIdBunch()).description(foodBunchDTO.getBunch().getDescription()).build();
		
		for(FoodDTO foodDTO: foodBunchDTO.getFoods()) {
			Food food = Food.builder().idFood(foodDTO.getIdFood()).build();
			FoodBunch foodBunch = getAllFoodBunch.stream().filter(x -> food.getIdFood().equals(x.getFood().getIdFood())).findAny().orElse(null);
			this.foodBunchRepository.save(new FoodBunch(foodBunch != null ? foodBunch.getIdFoodBunch() : null, food, bunch));
		}
		
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<FoodBunchDTO>> getAll() {
		return ResponseEntity.ok(FoodBunchDTO.converter(this.foodBunchRepository.getAll()));
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) {
		Optional<FoodBunch> foodBunch = this.foodBunchRepository.findById(id);
		if (foodBunch.isPresent()) {
			foodBunchRepository.delete(foodBunch.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}
	
	public ResponseEntity<List<FoodBunchDTO>> getFoodTable(Long idCompositionTable) {
		return ResponseEntity.ok(FoodBunchDTO.converter(this.foodBunchRepository.getFoodTable(idCompositionTable)));
	}
	 
}
