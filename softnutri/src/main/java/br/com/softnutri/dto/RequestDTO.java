package br.com.softnutri.dto;

import br.com.softnutri.domain.User;
import lombok.Data;

@Data
public class RequestDTO {

	private Long idRequest;
	private User user;
	private String name;
	private Long amount;
	private String deliveryAddress;
	private String observation;
}
