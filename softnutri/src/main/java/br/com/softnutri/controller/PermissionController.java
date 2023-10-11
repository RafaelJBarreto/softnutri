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
import br.com.softnutri.dto.BunchDTO;
import br.com.softnutri.dto.PermissionDTO;
import br.com.softnutri.dto.PersonPaperDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.service.BunchService;
import br.com.softnutri.service.PermissionService;

@RestController
@RequestMapping("/permission")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PermissionController {

	private final BunchService bunchService;
	private final PermissionService permissionService;
	
	@Autowired
	public PermissionController(BunchService bunchService, PermissionService permissionService) {
		this.bunchService = bunchService;
		this.permissionService = permissionService;
	}

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody PersonPaperDTO dto) throws SoftNutriException {
		return this.permissionService.save(dto);
	}

	@GetMapping("/")
	public ResponseEntity<List<BunchDTO>> findAll() throws SoftNutriException{
		return this.bunchService.listAll(); 
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) throws SoftNutriException {
		return this.bunchService.delete(id);
	}
	
	@GetMapping("/find/{id}")
	public List<PermissionDTO> getFindById(@PathVariable(value = "id")  Long idUsuario) throws SoftNutriException {
		return permissionService.getPermission(idUsuario);
	}
}
