package br.com.softnutri.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.interfaces.PermissionReturn;
import lombok.Data;

@Data
public class PaperDTO {
	private Long idPaper;
	private Integer get;
	private Integer post;
	private Integer put;
	private Integer delete;
		
	public PaperDTO(Long idPaper, Integer get, Integer post, Integer put, Integer delete) {
		this.idPaper = idPaper;
		this.get = get;
		this.post = post;
		this.put = put;
		this.delete = delete;
	}
			
	public static List<PaperDTO> converter(List<ModuleRole> modules) {
		List<PaperDTO> resultado = new ArrayList<>();
		for(ModuleRole mr: modules) {
			resultado.add(new PaperDTO(mr.getPaper().getIdPaper(), mr.getPaper().getGet() != null ? 0 : -1, mr.getPaper().getPost() != null ? 0 : -1, mr.getPaper().getPut() != null ? 0 : -1, mr.getPaper().getDelete() != null ? 0 : -1));
		}
		return resultado;
		
	}
	
	public static List<PaperDTO> converterPermissionTrue(List<PermissionReturn> modules) {
		List<PaperDTO> resultado = new ArrayList<>();
		for(PermissionReturn mr: modules) {
			resultado.add(new PaperDTO(mr.getIdPaper(), mr.getRegister(), mr.getPost(), mr.getPut(), mr.getDeletar()));
		}
		return resultado;
		
	}
	
	public static List<Long> converterToLong(List<PermissionReturn> list) {
		return list.stream().map(PermissionReturn::getIdPaper).toList();
	}

}
