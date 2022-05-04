package br.com.softnutri.service;

import org.springframework.stereotype.Service;


import br.com.softnutri.dominio.Papel;
import br.com.softnutri.repository.PapelRepository;

@Service(value = "papelService")
public class PapelService {
	
	private final PapelRepository papelRepository;

	public PapelService(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}
	
	public Papel findByDescription(String descricao) {
		return this.papelRepository.findByDescricao(descricao);
    }
	

}
