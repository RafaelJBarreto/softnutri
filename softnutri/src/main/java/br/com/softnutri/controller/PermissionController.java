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
import br.com.softnutri.records.PermissionDTO;
import br.com.softnutri.records.PersonPaperDTO;
import br.com.softnutri.service.PermissionService;

@RestController
@RequestMapping("/permission")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PermissionController {

	private final PermissionService permissionService;
	
	@Autowired
	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody PersonPaperDTO dto) {
		this.permissionService.save(dto);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<List<PermissionDTO>> getFindById(@PathVariable(value = "id")  Long idUsuario) {
		return ResponseEntity.ok(permissionService.getPermission(idUsuario));
	}
}
