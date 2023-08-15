package br.com.softnutri.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PermissionDTO {

	private long idModule;
	private String description;
	private boolean checked;
	private List<PaperDTO> paper = new ArrayList<>();
	
	public PermissionDTO(long idModule, String description, List<PaperDTO> paper) {
		this.idModule = idModule;
		this.description = description;
		this.paper = paper;
	}

}
