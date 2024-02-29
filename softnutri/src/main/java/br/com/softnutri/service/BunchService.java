package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Bunch;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.records.BunchDTO;
import br.com.softnutri.repository.BunchRepository;

@Service
public class BunchService {
	
	private final BunchRepository bunchRepository;

	@Autowired
	public BunchService(BunchRepository bunchRepository) {
		this.bunchRepository = bunchRepository;
	}
	
	public void save(BunchDTO br){ 
		try {
			this.bunchRepository.save(Bunch.builder().idBunch(br.idBunch()).description(br.description()).build());
		}catch (Exception e) {
			throw new SoftNutriException("BUNCH.ERROR_SAVE_BUNCH", e);
		}
	}
	
	public List<BunchDTO> listAll() { 
		try {
			return this.bunchRepository.findAll().stream().map(obj -> new BunchDTO(obj.getIdBunch(), obj.getDescription())).toList();
		}catch (Exception e) {
			throw new SoftNutriException("BUNCH.ERROR_LIST_BUNCH", e);
		}
	}
	
	public void delete(Long id) {
		try {
			final Optional<Bunch> bunch = this.bunchRepository.findById(id);
			bunch.ifPresent(bunchRepository::delete); 
		}catch (Exception e) {
			throw new SoftNutriException("BUNCH.BUNCH_REMOVE_ERROR", e);
		}
	}
	 
}
