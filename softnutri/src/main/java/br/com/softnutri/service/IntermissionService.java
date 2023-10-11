package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Intermission;
import br.com.softnutri.dto.IntermissionDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.repository.IntermissionRepository;

@Service
public class IntermissionService {
	
	private final IntermissionRepository intermissionRepository;

	@Autowired
	public IntermissionService(IntermissionRepository intermissionRepository) {
		this.intermissionRepository = intermissionRepository;
	}
	
	public ResponseEntity<MessageResponse> save(IntermissionDTO intermissionDTO)throws SoftNutriException { 
		try {
			this.intermissionRepository.save(
					Intermission.builder().idIntermission(intermissionDTO.getIdIntermission()).time(intermissionDTO.getTime()).build()
					
			);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		} catch (Exception e) {
			throw new SoftNutriException("Error save Intermission ", e);
		}	
	}
	
	public IntermissionDTO get() throws SoftNutriException{ 
		try {
			List<Intermission> intermission = this.intermissionRepository.findAll();
			if (!intermission.isEmpty()) {
				return IntermissionDTO.converter(intermission.get(0));
			} else {
				return null;
			}		
		} catch (Exception e) {
			throw new SoftNutriException("Error get Intermission ", e);
		}	
	}	 
}
