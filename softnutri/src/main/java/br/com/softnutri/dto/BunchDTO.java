package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Bunch;

public class BunchDTO {

	private Long idBunch;
	private String description;

	public BunchDTO() {
	}

	public BunchDTO(Long idBunch, String description) {
		this.idBunch = idBunch;
		this.description = description;
	}

	public BunchDTO(Bunch group) {
		this.idBunch = group.getIdBunch();
		this.description = group.getDescription();
	}

	public Long getIdBunch() {
		return idBunch;
	}

	public void setIdBunch(Long idBunch) {
		this.idBunch = idBunch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Bunch converterToDomain(BunchDTO groupDTO) {
		Bunch bunch = new Bunch();
		bunch.setIdBunch(groupDTO.getIdBunch());
		bunch.setDescription(groupDTO.getDescription());
		return bunch;
	}

	public static List<BunchDTO> converter(List<Bunch> foodGroups) {
		return foodGroups.stream().map(BunchDTO::new).toList();
	}
}
