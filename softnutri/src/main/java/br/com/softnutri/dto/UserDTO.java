package br.com.softnutri.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.domain.Phone;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.service.UserService;
import br.com.softnutri.util.Criptografia;

public class UserDTO extends PersonDTO {

	private String password;
	private String language;
	private List<PaperDTO> paper;
	private UserType userType;
	private String crn;
	private LocalDateTime dateRegister;
	
	public UserDTO() {
		super();
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<PaperDTO> getPaper() {
		return paper;
	}

	public void setPaper(List<PaperDTO> paper) {
		this.paper = paper;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}
	
	public LocalDateTime getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDateTime dateRegister) {
		this.dateRegister = dateRegister;
	}

	public static List<UserDTO> converter(List<User> users) {
		return users.stream().map(UserDTO::new).toList();
	}

	public static UserDTO converter(User user) {
		return new UserDTO(user);
	}

	public static User converterToDomain(UserDTO userDTO, UserService userService) {
		User user = new User();
		User userAux = null;
		user.setIdPerson(userDTO.getIdPerson());
		user.setCpf(userDTO.getCpf());
		user.setBirthDate(LocalDate.now());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setName(userDTO.getName());
		user.setGender(userDTO.getGender());
		user.setLanguage(userDTO.getLanguage());
		user.setUserType(userDTO.getUserType());
		user.setCrn(userDTO.getCrn());
		
		if(userDTO.getIdPerson() != null) {
			userAux = userService.getUserById(userDTO.getIdPerson());
		}
		
		if(userAux != null) {
			if(userDTO.getPassword() == null) {
				user.setPassword(userAux.getPassword());
			}else {
				user.setPassword(userDTO.getPassword());
			}
			
			user.setPaper(userAux.getPaper());
			user.setDateRegister(userAux.getDateRegister());
		}
		
		List<Phone> phones = new ArrayList<>();
		for (PhoneDTO phone : userDTO.getPhones()) {
			Phone ph = new Phone();
			ph.setIdPhone(phone.getIdPhone() > 0 ? phone.getIdPhone() : null);
			ph.setNumber(phone.getNumero());
			ph.setPerson(user);
			phones.add(ph);
		}
		user.setPhones(phones);
		return user;
	}
}
