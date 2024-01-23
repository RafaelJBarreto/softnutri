package br.com.softnutri.record;

public record BunchRecord(Long idBunch, String description) {

	public BunchRecord{
		if(description == null) {
			description = "GLOBAL.WITHOUTGROUP";
		}
	}
	
}
