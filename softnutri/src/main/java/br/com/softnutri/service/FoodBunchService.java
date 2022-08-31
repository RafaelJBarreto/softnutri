package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Bunch;
import br.com.softnutri.domain.Food;
import br.com.softnutri.domain.FoodBunch;
import br.com.softnutri.dto.BunchDTO;
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
		Bunch bunch = BunchDTO.converterToDomain(foodBunchDTO.getBunch());
		
		for(FoodDTO foodDTO: foodBunchDTO.getFoods()) {
			Food food = FoodDTO.converterToDomain(foodDTO);
			FoodBunch foodBunch = getAllFoodBunch.stream().filter(x -> food.getIdFood().equals(x.getFood().getIdFood())).findAny().orElse(null);
			this.foodBunchRepository.save(new FoodBunch(foodBunch != null ? foodBunch.getIdFoodBunch() : null, food, bunch));
		}
		
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<FoodBunchDTO>> getAll() {
		return ResponseEntity.ok(FoodBunchDTO.converter(this.foodBunchRepository.getAll()));
	}
	 
}
