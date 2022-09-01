package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Bunch;
import br.com.softnutri.dto.BunchDTO;
import br.com.softnutri.repository.BunchRepository;

@Service
public class BunchService {
	
	private final BunchRepository bunchRepository;

	@Autowired
	public BunchService(BunchRepository bunchRepository) {
		this.bunchRepository = bunchRepository;
	}
	
	public ResponseEntity<MessageResponse> save(BunchDTO groupDTO) { 
		this.bunchRepository.save(BunchDTO.converterToDomain(groupDTO));
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<BunchDTO>> listAll() { 
		return ResponseEntity.ok(BunchDTO.converter(this.bunchRepository.findAll()));
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) {
		Optional<Bunch> bunch = this.bunchRepository.findById(id);
		if (bunch.isPresent()) {
			bunchRepository.delete(bunch.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}
	 
}
