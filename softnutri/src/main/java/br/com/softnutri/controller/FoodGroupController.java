package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.FoodGroupAssociationDTO;
import br.com.softnutri.dto.FoodGroupDTO;
import br.com.softnutri.service.FoodGroupService;

@RestController
@RequestMapping("/foodGroup")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FoodGroupController {

	@Autowired
	private FoodGroupService foodGroupService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> logoutUsuario(@RequestBody FoodGroupDTO dto) {
		return this.foodGroupService.save(dto);
	}
	
	@GetMapping("/association/")
	public ResponseEntity<List<FoodGroupDTO>> findAll(@RequestBody FoodGroupAssociationDTO dto){
		return this.foodGroupService.listAll(); 
	}
}
