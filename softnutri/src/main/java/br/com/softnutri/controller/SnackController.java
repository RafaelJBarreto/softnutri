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
import br.com.softnutri.records.SnackDTO;
import br.com.softnutri.service.SnackService;

@RestController
@RequestMapping("/snack")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SnackController {

	@Autowired
	private SnackService snackService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody SnackDTO dto) {
		this.snackService.save(dto);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}

	@GetMapping("/")
	public ResponseEntity<List<SnackDTO>> findAll() {
		return ResponseEntity.ok(this.snackService.listAll());
	}
	
	@GetMapping(value = "/get/{id}")
	public SnackDTO get(@PathVariable(value = "id") Long id) {
		return this.snackService.get(id);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) {
		this.snackService.delete(id);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
	}
}
