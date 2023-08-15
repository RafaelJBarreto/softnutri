package br.com.softnutri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.dto.PaperDTO;
import br.com.softnutri.dto.PermissionDTO;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.util.Util;

@Service
public class PermissionService{

	private final ModuleRoleRepository moduleRoleRepository;

	@Autowired
	public PermissionService(ModuleRoleRepository moduleRoleRepository) {
		this.moduleRoleRepository = moduleRoleRepository;
	}
	
	public List<PermissionDTO> getPermission(Long idUsuario){
		List<ModuleRole> listPermission =  this.moduleRoleRepository.getPermissionPerson(idUsuario);
		List<PermissionDTO> resultado = arrangePermissionExists(listPermission);
		List<ModuleRole> list = null;
		if(listPermission.isEmpty()) {
			list = this.moduleRoleRepository.findAll();
		}else {
			list = this.moduleRoleRepository.getNotPermissionPerson(PaperDTO.converterToLong(listPermission));
		}
		resultado.addAll(arrangePermission(list));
		return resultado;
	}
	
	private List<PermissionDTO> arrangePermissionExists (List<ModuleRole> list){
		List<PermissionDTO> resultado = new ArrayList<>();
		List<ModuleRole> modules = list.stream() .filter(Util.distinctByKey(p -> p.getModule().getIdModule())).toList();
		for(ModuleRole mr: modules) {			
			List<ModuleRole> filter = list.stream().filter(d -> mr.getModule().getIdModule().equals(d.getModule().getIdModule())).toList();
			resultado.add(new PermissionDTO(mr.getModule().getIdModule(), mr.getModule().getName(), filter.size() < 4 ? PaperDTO.converterExists(filter) : PaperDTO.converterExistsAll(filter)));
		}
		return resultado;
	}
	
	private List<PermissionDTO> arrangePermission (List<ModuleRole> list){
		List<PermissionDTO> resultado = new ArrayList<>();
		List<ModuleRole> modules = list.stream() .filter(Util.distinctByKey(p -> p.getModule().getIdModule())).toList();
		for(ModuleRole mr: modules) {			
			resultado.add(new PermissionDTO(mr.getModule().getIdModule(), mr.getModule().getName(), PaperDTO.converter(list.stream().filter(d -> mr.getModule().getIdModule().equals(d.getModule().getIdModule())).toList())));
		}
		return resultado;
	}
	
	
}
