package br.com.softnutri.dto;

import br.com.softnutri.domain.Phone;
import br.com.softnutri.util.Criptografia;
import lombok.Data;

@Data
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
}
