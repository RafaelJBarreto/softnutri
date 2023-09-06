package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Food;
import br.com.softnutri.dto.FoodDTO;
import br.com.softnutri.repository.FoodRepository;

@Service
public class FoodService {
	
	private final FoodRepository foodRepository;
	
	@Autowired
	public FoodService(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	public ResponseEntity<MessageResponse> save(FoodDTO foodDTO) { 
		this.foodRepository.save(new Food(foodDTO.getIdFood(), foodDTO.getDescription(), foodDTO.getDescriptionPreparation(), foodDTO.getNutritionalData().getCalories(),foodDTO.getNutritionalData().getProtein(),foodDTO.getNutritionalData().getLipids(),foodDTO.getNutritionalData().getCarbohydrate()));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	public ResponseEntity<List<FoodDTO>> listAll() { 
		return ResponseEntity.ok(FoodDTO.converter(this.foodRepository.findAll()));
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) {
		Optional<Food> food = this.foodRepository.findById(id);
		if (food.isPresent()) {
			foodRepository.delete(food.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}


}
