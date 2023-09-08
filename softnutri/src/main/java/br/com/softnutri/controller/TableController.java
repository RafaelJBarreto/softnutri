package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.CompositionTableDTO;
import br.com.softnutri.service.TableService;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TableController {

	@Autowired
	private TableService tableService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody CompositionTableDTO dto) {
		return this.tableService.save(dto);
	}

	@GetMapping("/")
	public ResponseEntity<List<CompositionTableDTO>> findAll(){
		return this.tableService.listAll(); 
	}
	
	@GetMapping(value = "/get/{id}")
	public CompositionTableDTO get(@PathVariable(value = "id") Long id) {
		return this.tableService.get(id);
	}
}
