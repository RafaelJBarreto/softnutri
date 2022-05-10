package br.com.softnutri.dto;

import br.com.softnutri.domain.Phone;

public class PhoneDTO {

	private String number;

	public PhoneDTO(Phone phone) {
		this.number = (phone.getNumber());
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
