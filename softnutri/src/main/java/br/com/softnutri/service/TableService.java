package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.CompositionTable;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.records.CompositionTableDTO;
import br.com.softnutri.repository.TableRepository;

@Service
public class TableService {
	
	private final TableRepository tableRepository;

	@Autowired
	public TableService(TableRepository tableRepository) {
		this.tableRepository = tableRepository;
	}
	
	public void save(CompositionTableDTO ctr) { 
		try {
			this.tableRepository.save(CompositionTable.builder().idCompositionTable(ctr.idCompositionTable()).name(ctr.name()).description(ctr.description()).build());
		}catch (Exception e) {
			throw new SoftNutriException("TABLE.ERROR_SAVE_TABLE", e);
		}
	}
	
	public List<CompositionTableDTO> listAll() { 
		try {
			return this.tableRepository.findAll().stream().map(obj -> new CompositionTableDTO(obj.getIdCompositionTable(), obj.getName(), obj.getDescription())).toList();
		}catch (Exception e) {
			throw new SoftNutriException("TABLE.ERROR_LIST_TABLE", e);
		}
	}
	
	public CompositionTableDTO get(Long idCompositionTable){
		try {
			final Optional<CompositionTable> ct = this.tableRepository.findById(idCompositionTable);
			if (ct.isPresent()) {
				return new CompositionTableDTO(ct.get().getIdCompositionTable(), ct.get().getName(), ct.get().getDescription());
			} else {
				throw new SoftNutriException("TABLE.ERROR_DADO_TABLE");
			}
		}catch (Exception e) {
			throw new SoftNutriException("TABLE.ERROR_DADO_TABLE", e);
		}
	}
	
	public void delete(Long id) {
		try {
			final Optional<CompositionTable> ct = this.tableRepository.findById(id);
			ct.ifPresent(this.tableRepository::delete);
		}catch (Exception e) {
			throw new SoftNutriException("TABLE.ERROR_DELETE_TABLE", e);
		}
	}		 
}
