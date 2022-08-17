package br.com.softnutri.dto;

import br.com.softnutri.domain.Phone;
import br.com.softnutri.util.Criptografia;

public class PhoneDTO {

	private Long idPhone;
	private String numero;

	public PhoneDTO() {
	}

	public PhoneDTO(Phone phone) {
		this.idPhone = phone.getIdPhone();
		this.numero = Criptografia.decode(phone.getNumber());
	}
	
	public PhoneDTO(String numero) {
		super();
		this.numero = numero;
	}

	public long getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(long idPhone) {
		this.idPhone = idPhone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
