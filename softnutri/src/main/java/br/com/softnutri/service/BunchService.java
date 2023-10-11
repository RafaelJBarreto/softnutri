package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Bunch;
import br.com.softnutri.dto.BunchDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.repository.BunchRepository;

@Service
public class BunchService {
	
	private final BunchRepository bunchRepository;

	@Autowired
	public BunchService(BunchRepository bunchRepository) {
		this.bunchRepository = bunchRepository;
	}
	
	public ResponseEntity<MessageResponse> save(BunchDTO groupDTO) throws SoftNutriException{ 
		try {
			this.bunchRepository.save(
					Bunch.builder().idBunch(groupDTO.getIdBunch()).description(groupDTO.getDescription()).build()
			);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		}catch (Exception e) {
			throw new SoftNutriException("Error save Bunch " + e.getMessage(), e);
		}
	}
	
	public ResponseEntity<List<BunchDTO>> listAll() throws SoftNutriException { 
		try {
			return ResponseEntity.ok(BunchDTO.converter(this.bunchRepository.findAll()));
		}catch (Exception e) {
			throw new SoftNutriException("Error list all Bunch ", e);
		}
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) throws SoftNutriException {
		try {
			Optional<Bunch> bunch = this.bunchRepository.findById(id);
			if (bunch.isPresent()) {
				bunchRepository.delete(bunch.get());
				return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
			} else {
				return ResponseEntity.ok(new MessageResponse("BUNCH.BUNCH_REMOVE_ERROR"));
			}
		}catch (Exception e) {
			throw new SoftNutriException("Error delete Bunch ", e);
		}
	}
	 
}
