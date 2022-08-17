package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.FoodGroup;
import br.com.softnutri.dto.FoodGroupDTO;
import br.com.softnutri.repository.FoodGroupRepository;

@Service
public class FoodGroupService {
	
	private final FoodGroupRepository foodGroupRepository;

	@Autowired
	public FoodGroupService(FoodGroupRepository foodGroupRepository) {
		this.foodGroupRepository = foodGroupRepository;
	}
	
	public ResponseEntity<MessageResponse> save(FoodGroupDTO foodGroupDTO) { 
		this.foodGroupRepository.save(new FoodGroup(foodGroupDTO));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<FoodGroupDTO>> listAll() { 
		return ResponseEntity.ok(FoodGroupDTO.converter(this.foodGroupRepository.findAll()));
	}
	 
}
