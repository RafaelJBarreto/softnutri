package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Intermission;

public class IntermissionDTO {

	private Long idIntermission;
	private int time;

	public IntermissionDTO() {
	}

	public IntermissionDTO(Long idIntermission, int time) {
		super();
		this.idIntermission = idIntermission;
		this.time = time;
	}

	public IntermissionDTO(Intermission intermission) {
		this.idIntermission = intermission.getIdIntermission();
		this.time = intermission.getTime();
	}

	public Long getIdIntermission() {
		return idIntermission;
	}

	public void setIdIntermission(Long idIntermission) {
		this.idIntermission = idIntermission;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public static IntermissionDTO converter(Intermission intermission) {
		return new IntermissionDTO(intermission);
	}

	public static Intermission converterToDomain(IntermissionDTO intermissionDTO) {
		Intermission intermission = new Intermission();
		intermission.setIdIntermission(intermissionDTO.getIdIntermission());
		intermission.setTime(intermissionDTO.getTime());
		return intermission;
	}

	public static List<IntermissionDTO> converter(List<Intermission> intermission) {
		return intermission.stream().map(IntermissionDTO::new).toList();
	}
}
