package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Snack;
import br.com.softnutri.dto.SnackDTO;
import br.com.softnutri.repository.SnackRepository;

@Service
public class SnackService {
	
	private final SnackRepository snackRepository;

	@Autowired
	public SnackService(SnackRepository snackRepository) {
		this.snackRepository = snackRepository;
	}
	
	public ResponseEntity<MessageResponse> save(SnackDTO snackDTO) { 
		this.snackRepository.save(SnackDTO.converterToDomain(snackDTO));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<SnackDTO>> listAll() { 
		return ResponseEntity.ok(SnackDTO.converter(this.snackRepository.findAll()));
	}
	
	public SnackDTO get(Long idSnack) {
		Optional<Snack> ct = this.snackRepository.findById(idSnack);
		if (ct.isPresent()) {
			return SnackDTO.converter(ct.get());
		} else {
			return null;
		}
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) {
		Optional<Snack> ct = this.snackRepository.findById(id);
		if (ct.isPresent()) {
			this.snackRepository.delete(ct.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}
		 
}
