package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.CompositionTable;
import lombok.Data;

@Data
public class CompositionTableDTO {

	private Long idCompositionTable;
	private String name;
	private String description;

	public CompositionTableDTO() {

	}

	public CompositionTableDTO(Long idCompositionTable, String name, String description) {
		this.idCompositionTable = idCompositionTable;
		this.name = name;
		this.description = description;
	}
	
	public CompositionTableDTO(CompositionTable compositionTable) {
		this.idCompositionTable = compositionTable.getIdCompositionTable();
		this.name = compositionTable.getName();
		this.description = compositionTable.getDescription();
	}

	public static List<CompositionTableDTO> converter(List<CompositionTable> compositionTable) {
		return compositionTable.stream().map(CompositionTableDTO::new).toList();
	}
	
	public static CompositionTableDTO converter(CompositionTable ct) {
		return new CompositionTableDTO(ct);
	}

}
