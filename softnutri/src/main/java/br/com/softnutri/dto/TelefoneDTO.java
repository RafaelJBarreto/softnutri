package br.com.softnutri.dto;

import br.com.softnutri.dominio.Telefone;

public class TelefoneDTO {

	private String numero;

	public TelefoneDTO(Telefone telefone) {
		this.numero = (telefone.getNumero());
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
