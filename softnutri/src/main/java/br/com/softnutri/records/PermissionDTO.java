package br.com.softnutri.records;

import java.util.List;

public record PermissionDTO(long idModule, String description, List<PaperDTO> paper, boolean checked) {

}
