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
import br.com.softnutri.dto.SnackDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.service.SnackService;

@RestController
@RequestMapping("/snack")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SnackController {

	@Autowired
	private SnackService snackService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody SnackDTO dto) throws SoftNutriException {
		return this.snackService.save(dto);
	}

	@GetMapping("/")
	public ResponseEntity<List<SnackDTO>> findAll() throws SoftNutriException {
		return this.snackService.listAll(); 
	}
	
	@GetMapping(value = "/get/{id}")
	public SnackDTO get(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return this.snackService.get(id);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return this.snackService.delete(id);
	}
}
