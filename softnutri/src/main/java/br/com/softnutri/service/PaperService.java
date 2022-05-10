package br.com.softnutri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.repository.PaperRepository;

@Service
public class PaperService {

	@SuppressWarnings("unused")
	private final PaperRepository papelRepository;

	@Autowired
	public PaperService(PaperRepository papelRepository) {
		this.papelRepository = papelRepository;
	}
	
	
}
