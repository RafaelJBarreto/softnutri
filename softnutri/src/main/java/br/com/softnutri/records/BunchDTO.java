package br.com.softnutri.records;

public record BunchDTO(Long idBunch, String description) {

	public BunchDTO{
		if(description == null) {
			description = "GLOBAL.WITHOUTGROUP";
		}
	}
	
}
