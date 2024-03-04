package br.com.softnutri.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.records.PaperDTO;
import br.com.softnutri.util.Criptografia;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserDTO extends PersonDTO {

	private String password;
	private String language;
	private List<PaperDTO> paper;
	private UserType userType;
	private String crn;
	private LocalDateTime dateRegister;
	
	public UserDTO() {
	}

	public UserDTO(User user) {
		super(user.getIdPerson(), Criptografia.decode(user.getName()), Criptografia.decode(user.getEmail()), Criptografia.decode(user.getCpf()), user.getBirthDate(), 
				Criptografia.decode(user.getAddress()), user.getGender());
		super.phones = new ArrayList<>();
		super.phones.addAll(user.getPhones().stream().map(PhoneDTO::new).toList());
		this.language = user.getLanguage();
		this.userType = user.getUserType();
		this.crn = user.getCrn();
		this.dateRegister = user.getDateRegister();
	}

	public static List<UserDTO> converter(List<User> users) {
		return users.stream().map(UserDTO::new).toList();
	}

	public static UserDTO converter(User user) {
		return new UserDTO(user);
	}
}
