package br.com.softnutri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.interfaces.PermissionReturn;
import br.com.softnutri.records.PaperDTO;
import br.com.softnutri.repository.PaperRepository;

@Service
public class PaperService {

	@SuppressWarnings("unused")
	private final PaperRepository papelRepository;

	@Autowired
	public PaperService(PaperRepository papelRepository) {
		this.papelRepository = papelRepository;
	}
	
	public List<PaperDTO> converterPermission(List<ModuleRole> modules) {
		final List<PaperDTO> resultado = new ArrayList<>();
		modules.forEach(mr -> 
			resultado.add(new PaperDTO(mr.getPaper().getIdPaper(), mr.getPaper().getGet() != null ? 0 : -1, mr.getPaper().getPost() != null ? 0 : -1, mr.getPaper().getPut() != null ? 0 : -1, mr.getPaper().getDelete() != null ? 0 : -1))
		);
		return resultado;
		
	}
	
	public List<PaperDTO> converterPermissionTrue(List<PermissionReturn> modules) {
		final List<PaperDTO> resultado = new ArrayList<>();
		modules.forEach(mr -> 
			resultado.add(new PaperDTO(mr.getIdPaper(), mr.getRegister(), mr.getPost(), mr.getPut(), mr.getDeletar()))
		);
		return resultado;
		
	}
	
	
}
