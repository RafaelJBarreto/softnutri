package br.com.softnutri.records;

import java.util.List;

public record PersonPaperDTO(Long idPerson, List<PermissionDTO> permission) {

}
