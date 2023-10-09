package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Intermission;
import lombok.Data;

@Data
public class IntermissionDTO {

	private Long idIntermission;
	private int time;

	public IntermissionDTO() {
	}

	public IntermissionDTO(Long idIntermission, int time) {
		this.idIntermission = idIntermission;
		this.time = time;
	}

	public IntermissionDTO(Intermission intermission) {
		this.idIntermission = intermission.getIdIntermission();
		this.time = intermission.getTime();
	}
	
	public static IntermissionDTO converter(Intermission intermission) {
		return new IntermissionDTO(intermission);
	}

	public static List<IntermissionDTO> converter(List<Intermission> intermission) {
		return intermission.stream().map(IntermissionDTO::new).toList();
	}
}
