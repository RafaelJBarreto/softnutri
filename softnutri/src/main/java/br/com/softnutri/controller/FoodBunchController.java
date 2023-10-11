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
import br.com.softnutri.dto.FoodBunchDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.service.FoodBunchService;

@RestController
@RequestMapping("/foodBunch")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FoodBunchController {

	@Autowired
	private FoodBunchService foodBunchService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody FoodBunchDTO dto) throws SoftNutriException {
		return foodBunchService.save(dto);
	}

	@GetMapping("/")
	public ResponseEntity<List<FoodBunchDTO>> findAll() throws SoftNutriException {
		return foodBunchService.getAll();
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return this.foodBunchService.delete(id);
	}
	
	@GetMapping(value = "/table/{id}")
	public ResponseEntity<List<FoodBunchDTO>> getFoodTable(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return this.foodBunchService.getFoodTable(id);
	}
}
