package br.com.softnutri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.IntermissionDTO;
import br.com.softnutri.service.IntermissionService;

@RestController
@RequestMapping("/intermission")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IntermissionController {

	@Autowired
	private IntermissionService intermissionService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody IntermissionDTO dto) {
		return this.intermissionService.save(dto);
	}

	@GetMapping("/get")
	public IntermissionDTO get() {
		return intermissionService.get();
	}
	
}
