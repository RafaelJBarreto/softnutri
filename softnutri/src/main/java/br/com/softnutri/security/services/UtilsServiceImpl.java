package br.com.softnutri.security.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl {
	
	private String getUserLogged() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		    return ((UserDetails)principal).getUsername();
		}
		
		return principal.toString();
	}
	
	public boolean verifyUserLogged(String username) {
		return this.getUserLogged().equals(username);
	}

}
