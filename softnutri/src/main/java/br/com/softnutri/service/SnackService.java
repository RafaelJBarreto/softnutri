package br.com.softnutri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void save(SnackDTO snackDTO) { 
		try {
			this.snackRepository.save(Snack.builder().idSnack(snackDTO.getIdSnack()).name(snackDTO.getName()).description(snackDTO.getDescription()).build());
		}catch (Exception e) {
			throw new SoftNutriException("SNACK.ERROR_SAVE_SNACK", e);
		}
	}
	
	public List<SnackDTO> listAll() { 
		try {
			return SnackDTO.converter(this.snackRepository.findAll());
		}catch (Exception e) {
			throw new SoftNutriException("SNACK.ERROR_LIST_SNACK", e);
		}
	}
	
	public SnackDTO get(Long idSnack) {
		try {
			final Optional<Snack> ct = this.snackRepository.findById(idSnack);
			if (ct.isPresent()) {
				return SnackDTO.converter(ct.get());
			} else {
				throw new SoftNutriException("SNACK.ERROR_DADO_SNACK");
			}
		}catch (Exception e) {
			throw new SoftNutriException("SNACK.ERROR_DADO_SNACK", e);
		}
	}
	
	public void delete(Long id) {
		try {
			final Optional<Snack> ct = this.snackRepository.findById(id);
			ct.ifPresent(this.snackRepository::delete);
		}catch (Exception e) {
			throw new SoftNutriException("SNACK.ERROR_DELETE_SNACK", e);
		}
	}
		 
}
