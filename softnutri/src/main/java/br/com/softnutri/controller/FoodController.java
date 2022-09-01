package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.FoodDTO;
import br.com.softnutri.service.FoodService;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/save")
	public ResponseEntity<MessageResponse> salvar(@RequestBody FoodDTO dto) {
		return this.foodService.save(dto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<FoodDTO>> findAll(){
		return this.foodService.listAll(); 
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) {
		return this.foodService.delete(id);
	}
}
