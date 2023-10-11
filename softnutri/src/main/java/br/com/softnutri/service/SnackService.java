package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Snack;
import br.com.softnutri.dto.SnackDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.repository.SnackRepository;

@Service
public class SnackService {
	
	private final SnackRepository snackRepository;

	@Autowired
	public SnackService(SnackRepository snackRepository) {
		this.snackRepository = snackRepository;
	}
	
	public ResponseEntity<MessageResponse> save(SnackDTO snackDTO) throws SoftNutriException { 
		try {
			this.snackRepository.save(
					Snack.builder().idSnack(snackDTO.getIdSnack()).name(snackDTO.getName()).description(snackDTO.getDescription()).build()
			);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		}catch (Exception e) {
			throw new SoftNutriException("Error save Snack " + e.getMessage(), e);
		}
	}
	
	public ResponseEntity<List<SnackDTO>> listAll() throws SoftNutriException { 
		try {
			return ResponseEntity.ok(SnackDTO.converter(this.snackRepository.findAll()));
		}catch (Exception e) {
			throw new SoftNutriException("Error list all Snack " + e.getMessage(), e);
		}
	}
	
	public SnackDTO get(Long idSnack) throws SoftNutriException {
		try {
			Optional<Snack> ct = this.snackRepository.findById(idSnack);
			if (ct.isPresent()) {
				return SnackDTO.converter(ct.get());
			} else {
				return null;
			}
		}catch (Exception e) {
			throw new SoftNutriException("Error get Snack " + e.getMessage(), e);
		}
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) throws SoftNutriException {
		try {
			Optional<Snack> ct = this.snackRepository.findById(id);
			if (ct.isPresent()) {
				this.snackRepository.delete(ct.get());
				return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
			} else {
				return ResponseEntity.ok(new MessageResponse("SNACK.ERROR_DELETE_SNACK"));
			}
		}catch (Exception e) {
			throw new SoftNutriException("Error delete Snack " + e.getMessage(), e);
		}
	}
		 
}
