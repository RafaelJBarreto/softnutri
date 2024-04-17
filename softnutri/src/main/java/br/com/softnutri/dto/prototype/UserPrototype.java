package br.com.softnutri.dto.prototype;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.softnutri.domain.PersonPaper;
import br.com.softnutri.domain.Phone;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.service.UserService;
import br.com.softnutri.util.Criptografia;

public class UserPrototype {
	
	public static User getUser(UserDTO userDTO, UserService userService) {
    	
    	String password = null;
    	Set<PersonPaper> paper = null;
    	LocalDateTime dateRegister = LocalDateTime.now();
    	
    	if(userDTO.getIdPerson() != null && userService != null) {
			final User user = userService.getUserById(userDTO.getIdPerson());
			if(user != null) {
				if(userDTO.getPassword() == null) {
					password = user.getPassword();
				}else {
					password = userDTO.getPassword();
				}
				paper = user.getPaper();
				dateRegister = user.getDateRegister();
			}
		}
    	
    	if(password != null) {
    		password = Criptografia.encoderSecurity(password);
    	}
		
    	final List<Phone> phones = new ArrayList<>();
		userDTO.getPhones().forEach(phone -> {
			final Phone ph = new Phone();
			ph.setIdPhone(phone.getIdPhone());
			ph.setNumber(Criptografia.encode(phone.getNumero()));
			ph.setPerson(User.builder().idPerson(userDTO.getIdPerson()).build());
			phones.add(ph);
		});
    	
    	return User.builder().idPerson(userDTO.getIdPerson()).address(Criptografia.encode(userDTO.getAddress())).cpf(Criptografia.encode(userDTO.getCpf())).birthDate(userDTO.getBirthDate()).email(Criptografia.encode(userDTO.getEmail())).
    	name(Criptografia.encode(userDTO.getName())).name(Criptografia.encode(userDTO.getName())).gender(userDTO.getGender()).language(userDTO.getLanguage()).userType(userDTO.getUserType()).
    	crn(userDTO.getCrn()).phones(phones).dateRegister(dateRegister).password(password).paper(paper).build();
	}
	
}
