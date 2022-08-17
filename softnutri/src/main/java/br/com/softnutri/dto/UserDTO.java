package br.com.softnutri.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.Phone;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.util.Criptografia;

public class UserDTO extends PersonDTO {

	private String password;
	private String language;
	private PaperDTO paper;
	private UserType userType;
	
	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		super(user.getIdPerson(), Criptografia.decode(user.getName()), Criptografia.decode(user.getEmail()), Criptografia.decode(user.getCpf()), user.getBirthDate(), 
				Criptografia.decode(user.getAddress()), user.getGender());
		super.phones = new ArrayList<>();
		super.phones.addAll(user.getPhones().stream().map(PhoneDTO::new).toList());
		this.language = user.getLanguage();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public PaperDTO getPaper() {
		return paper;
	}

	public void setPaper(PaperDTO paper) {
		this.paper = paper;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public static List<UserDTO> converter(List<User> users) {
		return users.stream().map(UserDTO::new).toList();
	}

	public static UserDTO converter(User user) {
		return new UserDTO(user);
	}

	public static User converterToDomain(UserDTO userDTO) {
		User user = new User();
		user.setIdPerson(userDTO.getIdPerson());
		user.setCpf(userDTO.getCpf());
		user.setBirthDate(LocalDate.now());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setName(userDTO.getName());
		user.setGender(userDTO.getGender());

		user.setPassword(userDTO.getPassword());
		user.setLanguage(userDTO.getLanguage());

		user.setUserType(userDTO.getUserType());
		List<Phone> phones = new ArrayList<>();
		for (PhoneDTO phone : userDTO.getPhones()) {
			Phone ph = new Phone();
			ph.setIdPhone(phone.getIdPhone());
			ph.setNumber(phone.getNumero());
			ph.setPerson(user);
			phones.add(ph);
		}
		user.setPhones(phones);
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
