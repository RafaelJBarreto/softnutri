package br.com.softnutri.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.ModuleRole;
import lombok.Data;

@Data
public class PaperDTO {
	private Integer get;
	private Integer post;
	private Integer put;
	private Integer delete;
		
	public PaperDTO(Integer get, Integer post, Integer put, Integer delete) {
		this.get = get;
		this.post = post;
		this.put = put;
		this.delete = delete;
	}
		
	public static List<PaperDTO> converterExists(List<ModuleRole> modules) {
		List<PaperDTO> resultado = new ArrayList<>();
		Integer get = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_GET")).toList().isEmpty() ? null : 1;
		Integer post = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_POST")).toList().isEmpty() ? null : 1;
		Integer put = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_PUT")).toList().isEmpty() ? null : 1;
		Integer delete = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_DELET")).toList().isEmpty() ? null : 1;
		resultado.add(new PaperDTO(get, post, put, delete));
		return resultado;
		
	}

	public static List<PaperDTO> converterExistsAll(List<ModuleRole> modules) {
		List<PaperDTO> resultado = new ArrayList<>();
		int get = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_GET")).toList().isEmpty() ? 0 : 1;
		int post = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_POST")).toList().isEmpty() ? 0 : 1;
		int put = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_PUT")).toList().isEmpty() ? 0 : 1;
		int delete = modules.stream() .filter(p -> p.getPaper().getDescription().contains("_DELET")).toList().isEmpty() ? 0 : 1;
		resultado.add(new PaperDTO(get, post, put, delete));
		return resultado;
		
	}
	
	public static List<PaperDTO> converter(List<ModuleRole> modules) {
		List<PaperDTO> resultado = new ArrayList<>();
		if(modules.size() < 4) {
			resultado.add(new PaperDTO(0, null, null, null));
		}else {
			resultado.add(new PaperDTO(0, 0, 0, 0));
		}
		return resultado;
		
	}
	
	public static List<Long> converterToLong(List<ModuleRole> list) {
		return list.stream().map(c -> c.getPaper().getIdPaper()).toList();
	}

}
