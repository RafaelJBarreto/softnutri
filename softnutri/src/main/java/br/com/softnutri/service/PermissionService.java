package br.com.softnutri.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.PersonPaper;
import br.com.softnutri.domain.User;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.interfaces.PermissionReturn;
import br.com.softnutri.records.PermissionDTO;
import br.com.softnutri.records.PersonPaperDTO;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.PersonPaperRepository;
import br.com.softnutri.util.Util;

@Service
public class PermissionService{

	private final ModuleRoleRepository moduleRoleRepository;
	private final PersonPaperRepository personPaperRepository;
	private final PaperService paperService;

	@Autowired
	public PermissionService(ModuleRoleRepository moduleRoleRepository, PersonPaperRepository personPaperRepository, PaperService paperService) {
		this.moduleRoleRepository = moduleRoleRepository;
		this.personPaperRepository = personPaperRepository;
		this.paperService = paperService;
	}
	
	public List<PermissionDTO> getPermission(Long idUsuario){
		try {
			final List<PermissionReturn> listPermission =  this.moduleRoleRepository.getPermissionPerson(idUsuario);
			final List<PermissionDTO> resultado = arrangePermissionTrue(listPermission);
			if(listPermission.isEmpty()) {
				resultado.addAll(arrangePermission(this.moduleRoleRepository.findAll()));
			}else {
				final List<ModuleRole> list = this.moduleRoleRepository.getNotPermissionPerson(listPermission.stream().map(PermissionReturn::getIdPaper).toList()).stream().map(c -> 
				ModuleRole.builder()
							.paper(
							   Paper.builder().idPaper(c.getIdPaper()).description(c.getDescription()).get(c.getAccess()).post(c.getSend()).put(c.getAlterar()).delete(c.getRemove()).build())
							.module( 
							   Module.builder().idModule(c.getIdModule()).name(c.getName()).build())
							.build()
							).toList();
				resultado.addAll(arrangePermission(list));
			}
			
			return resultado.stream().sorted(Comparator.comparing(PermissionDTO::idModule)).toList();
		} catch (Exception e) {
			throw new SoftNutriException("PERMISSION.LIST_PERMISSION", e);
		}
	}
	
	
	private List<PermissionDTO> arrangePermission (List<ModuleRole> list){
		final List<PermissionDTO> resultado = new ArrayList<>();
		final List<ModuleRole> modules = list.stream() .filter(Util.distinctByKey(p -> p.getModule().getIdModule())).toList();
		modules.forEach(mr -> 
			resultado.add(new PermissionDTO(mr.getModule().getIdModule(), mr.getModule().getName(), this.paperService.converterPermission(list.stream().filter(d -> mr.getModule().getIdModule().equals(d.getModule().getIdModule())).toList()), false))
		);
		return resultado;

	}
	
	private List<PermissionDTO> arrangePermissionTrue (List<PermissionReturn> list){
			final List<PermissionDTO> resultado = new ArrayList<>();
			final List<PermissionReturn> modules = list.stream() .filter(Util.distinctByKey(PermissionReturn::getIdModule)).toList();
			modules.forEach(mr -> 
				resultado.add(new PermissionDTO(mr.getIdModule(), mr.getName(), this.paperService.converterPermissionTrue(list.stream().filter(d -> mr.getIdModule().equals(d.getIdModule())).toList()), false))
			);
			return resultado;
	}

	public void save(PersonPaperDTO dto){
		try {
			final List<PersonPaper> permissions = this.personPaperRepository.findByUserIdPerson(dto.idPerson()).stream().toList();
			dto.permission().forEach(p -> 
				p.paper().forEach(pp -> {
					final PersonPaper personPaper = permissions.stream().filter(x -> pp.idPaper().equals(x.getPaper().getIdPaper())).findAny().orElse(null);
					this.personPaperRepository.save(
							PersonPaper.builder().idPersonPaper(personPaper == null ? null : personPaper.getIdPersonPaper()).user(User.builder().idPerson(dto.idPerson()).build()).paper( Paper.builder().idPaper(pp.idPaper()).build()).get(pp.get()).post(pp.post()).put(pp.put()).delete(pp.delete()).build());
				})
				
			);
		} catch (Exception e) {
			throw new SoftNutriException("PERMISSION.ERROR_SAVE", e);
		}
	}
		
}
