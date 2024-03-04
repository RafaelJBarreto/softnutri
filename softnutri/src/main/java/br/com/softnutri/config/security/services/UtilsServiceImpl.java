package br.com.softnutri.config.security.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl {
	
	private String getUserLogged() {
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails userdetails) {
		    return userdetails.getUsername();
		}
		return principal.toString();
	}
	
	public boolean verifyUserLogged(String username) {
		return this.getUserLogged().equals(username);
	}

}
