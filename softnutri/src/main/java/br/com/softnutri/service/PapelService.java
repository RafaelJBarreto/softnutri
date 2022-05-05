package br.com.softnutri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.repository.PapelRepository;

@Service
public class PapelService {

	private final PapelRepository papelRepository;

	@Autowired
	public PapelService(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}
	
	
}
