package br.com.softnutri.dto;

import br.com.softnutri.dominio.Telefone;

public class TelefoneDto {

	private String numero;

	public TelefoneDto(Telefone telefone) {
		this.numero = (telefone.getNumero());
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
