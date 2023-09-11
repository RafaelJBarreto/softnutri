package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Snack;
import lombok.Data;

@Data
public class SnackDTO {

	private Long idSnack;
	private String name;
	private String description;

	public SnackDTO() {

	}

	public SnackDTO(Long idSnack, String name, String description) {
		this.idSnack = idSnack;
		this.name = name;
		this.description = description;
	}

	public SnackDTO(Snack s) {
		this.idSnack = s.getIdSnack();
		this.name = s.getName();
		this.description = s.getDescription();
	}

	public static Snack converterToDomain(SnackDTO sDTO) {
		Snack s = new Snack();
		s.setIdSnack(sDTO.getIdSnack());
		s.setDescription(sDTO.getDescription());
		s.setName(sDTO.getName());
		return s;
	}

	public static List<SnackDTO> converter(List<Snack> snacks) {
		return snacks.stream().map(SnackDTO::new).toList();
	}

	public static SnackDTO converter(Snack s) {
		return new SnackDTO(s);
	}

}
