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
import br.com.softnutri.records.BunchDTO;
import br.com.softnutri.service.BunchService;

@RestController
@RequestMapping("/bunch")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BunchController {

	@Autowired
	private BunchService bunchService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody BunchDTO br) {
		this.bunchService.save(br);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}

	@GetMapping("/")
	public ResponseEntity<List<BunchDTO>> findAll(){
		return ResponseEntity.ok(this.bunchService.listAll()); 
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) {
		this.bunchService.delete(id);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
	}
}
