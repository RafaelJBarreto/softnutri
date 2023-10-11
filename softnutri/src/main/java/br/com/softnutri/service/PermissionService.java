package br.com.softnutri.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.PersonPaper;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.PaperDTO;
import br.com.softnutri.dto.PermissionDTO;
import br.com.softnutri.dto.PersonPaperDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.interfaces.PermissionReturn;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.PersonPaperRepository;
import br.com.softnutri.util.Util;

@Service
public class PermissionService{

	private final ModuleRoleRepository moduleRoleRepository;
	private final PersonPaperRepository personPaperRepository;

	@Autowired
	public PermissionService(ModuleRoleRepository moduleRoleRepository, PersonPaperRepository personPaperRepository) {
		this.moduleRoleRepository = moduleRoleRepository;
		this.personPaperRepository = personPaperRepository;
	}
	
	public List<PermissionDTO> getPermission(Long idUsuario) throws SoftNutriException{
		try {
			List<PermissionReturn> listPermission =  this.moduleRoleRepository.getPermissionPerson(idUsuario);
			List<PermissionDTO> resultado = arrangePermissionTrue(listPermission);
			if(listPermission.isEmpty()) {
				resultado.addAll(arrangePermission(this.moduleRoleRepository.findAll()));
			}else {
				List<ModuleRole> list = this.moduleRoleRepository.getNotPermissionPerson(PaperDTO.converterToLong(listPermission)).stream().map(c -> 
				ModuleRole.builder()
							.paper(
							   Paper.builder().idPaper(c.getIdPaper()).description(c.getDescription()).get(c.getAccess()).post(c.getSend()).put(c.getAlterar()).delete(c.getRemove()).build())
							.module( 
							   Module.builder().idModule(c.getIdModule()).name(c.getName()).build())
							.build()
							).toList();
				resultado.addAll(arrangePermission(list));
			}
			
			return resultado.stream().sorted(Comparator.comparing(PermissionDTO::getIdModule)).toList();
		} catch (Exception e) {
			throw new SoftNutriException("Error get permission user ", e);
		}
	}
	
	
	private List<PermissionDTO> arrangePermission (List<ModuleRole> list) throws SoftNutriException{
		try {
			List<PermissionDTO> resultado = new ArrayList<>();
			List<ModuleRole> modules = list.stream() .filter(Util.distinctByKey(p -> p.getModule().getIdModule())).toList();
			for(ModuleRole mr: modules) {	
				resultado.add(new PermissionDTO(mr.getModule().getIdModule(), mr.getModule().getName(), PaperDTO.converter(list.stream().filter(d -> mr.getModule().getIdModule().equals(d.getModule().getIdModule())).toList())));
			}
			return resultado;
		} catch (Exception e) {
			throw new SoftNutriException("Error arrange permission ", e);
		}
	}
	
	private List<PermissionDTO> arrangePermissionTrue (List<PermissionReturn> list) throws SoftNutriException{
		try {
			List<PermissionDTO> resultado = new ArrayList<>();
			List<PermissionReturn> modules = list.stream() .filter(Util.distinctByKey(PermissionReturn::getIdModule)).toList();
			for(PermissionReturn mr: modules) {	
				resultado.add(new PermissionDTO(mr.getIdModule(), mr.getName(), PaperDTO.converterPermissionTrue(list.stream().filter(d -> mr.getIdModule().equals(d.getIdModule())).toList())));
			}
			return resultado;
		} catch (Exception e) {
			throw new SoftNutriException("Error arrange permission true ", e);
		}
	}

	public ResponseEntity<MessageResponse> save(PersonPaperDTO dto) throws SoftNutriException{
		try {
			List<PersonPaper> permissions = this.personPaperRepository.findByUserIdPerson(dto.getIdPerson()).stream().toList();
			for (PermissionDTO p : dto.getPermission()) {
				for (PaperDTO pp : p.getPaper()) {
					PersonPaper personPaper = permissions.stream().filter(x -> pp.getIdPaper().equals(x.getPaper().getIdPaper())).findAny().orElse(null);
					this.personPaperRepository.save(
							PersonPaper.builder().idPersonPaper(personPaper == null ? null : personPaper.getIdPersonPaper()).user(User.builder().idPerson(dto.getIdPerson()).build()).paper( Paper.builder().idPaper(pp.getIdPaper()).build()).get(pp.getGet()).post(pp.getPost()).put(pp.getPut()).delete(pp.getDelete()).build());
				}
				
			}
			
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
		} catch (Exception e) {
			throw new SoftNutriException("Error save PersonPaper ", e);
		}
	}
		
}
