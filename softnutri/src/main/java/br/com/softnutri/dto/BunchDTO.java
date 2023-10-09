package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Bunch;
import lombok.Data;

@Data
public class BunchDTO {

	private Long idBunch;
	private String description;

	public BunchDTO() {
	}

	public BunchDTO(Long idBunch, String description) {
		this.idBunch = idBunch;
		this.description = description == null ? "GLOBAL.WITHOUTGROUP" :  description;
	}

	public BunchDTO(Bunch group) {
		this.idBunch = group.getIdBunch();
		this.description = group.getDescription();
	}

	public static List<BunchDTO> converter(List<Bunch> foodGroups) {
		return foodGroups.stream().map(BunchDTO::new).toList();
	}
}
