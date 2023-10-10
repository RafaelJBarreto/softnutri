package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.CompositionTable;
import br.com.softnutri.dto.CompositionTableDTO;
import br.com.softnutri.repository.TableRepository;

@Service
public class TableService {
	
	private final TableRepository tableRepository;

	@Autowired
	public TableService(TableRepository tableRepository) {
		this.tableRepository = tableRepository;
	}
	
	public ResponseEntity<MessageResponse> save(CompositionTableDTO compositionTableDTO) { 
		this.tableRepository.save(
				CompositionTable.builder().idCompositionTable(compositionTableDTO.getIdCompositionTable()).name(compositionTableDTO.getName()).description(compositionTableDTO.getDescription()).build()
		);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<CompositionTableDTO>> listAll() { 
		return ResponseEntity.ok(CompositionTableDTO.converter(this.tableRepository.findAll()));
	}
	
	public CompositionTableDTO get(Long idCompositionTable) {
		Optional<CompositionTable> ct = this.tableRepository.findById(idCompositionTable);
		if (ct.isPresent()) {
			return CompositionTableDTO.converter(ct.get());
		} else {
			return null;
		}
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) {
		Optional<CompositionTable> ct = this.tableRepository.findById(id);
		if (ct.isPresent()) {
			this.tableRepository.delete(ct.get());
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
		} else {
			return null;
		}
	}
		 
}
