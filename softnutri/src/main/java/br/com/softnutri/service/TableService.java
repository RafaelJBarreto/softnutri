package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.CompositionTable;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.record.CompositionTableRecord;
import br.com.softnutri.repository.TableRepository;

@Service
public class TableService {
	
	private final TableRepository tableRepository;

	@Autowired
	public TableService(TableRepository tableRepository) {
		this.tableRepository = tableRepository;
	}
	
	public ResponseEntity<MessageResponse> save(CompositionTableRecord ctr) throws SoftNutriException { 
		try {
			this.tableRepository.save(
					CompositionTable.builder().idCompositionTable(ctr.idCompositionTable()).name(ctr.name()).description(ctr.description()).build()
			);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		}catch (Exception e) {
			throw new SoftNutriException("Error save CompositionTable" + e.getMessage(), e);
		}
	}
	
	public ResponseEntity<List<CompositionTableRecord>> listAll() throws SoftNutriException { 
		try {
			return ResponseEntity.ok(this.tableRepository.findAll().stream().map(obj -> new CompositionTableRecord(obj.getIdCompositionTable(), obj.getName(), obj.getDescription())).toList());
		}catch (Exception e) {
			throw new SoftNutriException("Error list all CompositionTable " + e.getMessage(), e);
		}
	}
	
	public CompositionTableRecord get(Long idCompositionTable) throws SoftNutriException{
		try {
			Optional<CompositionTable> ct = this.tableRepository.findById(idCompositionTable);
			if (ct.isPresent()) {
				return new CompositionTableRecord(ct.get().getIdCompositionTable(), ct.get().getName(), ct.get().getDescription());
			} else {
				return null;
			}
		}catch (Exception e) {
			throw new SoftNutriException("Error get CompositionTable " + e.getMessage(), e);
		}
	}
	
	public ResponseEntity<MessageResponse> delete(Long id) throws SoftNutriException {
		try {
			Optional<CompositionTable> ct = this.tableRepository.findById(id);
			if (ct.isPresent()) {
				this.tableRepository.delete(ct.get());
				return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_REMOVE"));
			} else {
				return ResponseEntity.ok(new MessageResponse("TABLE.ERROR_DELETE_TABLE"));
			}
		}catch (Exception e) {
			throw new SoftNutriException("Error delete CompositionTable " + e.getMessage(), e);
		}
	}
		 
}
