package br.com.softnutri.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PersonPaperDTO {
	private Long idPerson;
	private List<PermissionDTO> permission = new ArrayList<>();
}
