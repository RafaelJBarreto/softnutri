package br.com.softnutri.service;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	
	private final ModelMapper modelMapper;

	@Autowired
	public FoodGroupService(FoodGroupRepository foodGroupRepository, ModelMapper modelMapper) {
		this.foodGroupRepository = foodGroupRepository;
		this.modelMapper = modelMapper;
	}
	
	public ResponseEntity<MessageResponse> save(FoodGroupDTO foodGroupDTO) { 
		this.foodGroupRepository.save(this.modelMapper.map(foodGroupDTO, FoodGroup.class));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<FoodGroupDTO>> listAll() { 
		return ResponseEntity.ok( this.foodGroupRepository.findAll().stream().map(f -> this.modelMapper.map(f,FoodGroupDTO.class)).toList());
	}
	 
}
