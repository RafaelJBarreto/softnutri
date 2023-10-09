package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Intermission;
import br.com.softnutri.dto.IntermissionDTO;
import br.com.softnutri.repository.IntermissionRepository;

@Service
public class IntermissionService {
	
	private final IntermissionRepository intermissionRepository;

	@Autowired
	public IntermissionService(IntermissionRepository intermissionRepository) {
		this.intermissionRepository = intermissionRepository;
	}
	
	public ResponseEntity<MessageResponse> save(IntermissionDTO intermissionDTO) { 
		this.intermissionRepository.save(new Intermission(intermissionDTO));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public IntermissionDTO get() { 
		List<Intermission> intermission = this.intermissionRepository.findAll();
		if (!intermission.isEmpty()) {
			return IntermissionDTO.converter(intermission.get(0));
		} else {
			return null;
		}
	}	 
}
