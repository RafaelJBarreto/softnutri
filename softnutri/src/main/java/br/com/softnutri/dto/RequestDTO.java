package br.com.softnutri.dto;

import br.com.softnutri.dominio.Usuario;

public class RequestDTO {

	private Long idRequest;
	private Usuario user;
	private String name;
	private Long amount;
	private String deliveryAddress;
	private String observation;

	public Long getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Long idRequest) {
		this.idRequest = idRequest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
