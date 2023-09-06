package br.com.softnutri.config.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.softnutri.domain.PersonPaper;
import br.com.softnutri.domain.User;
import br.com.softnutri.util.Util;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	  private Long id;

	  private String username;

	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	  public UserDetailsImpl(Long id, String username, String password,
	      Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.username = username;
	    this.password = password;
	    this.authorities = authorities;
	  }

	  public static UserDetailsImpl build(User user, Set<PersonPaper> paper) {
		  List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		  for(PersonPaper pp: paper) {
			  String roles = Util.getRole(pp);
			  if(!roles.equals("")) {
				  authorities.add(new SimpleGrantedAuthority(roles));
			  }
		  }

	    return new UserDetailsImpl(user.getIdPerson(), user.getEmail(),  user.getPassword(),  authorities);
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public Long getId() {
	    return id;
	  }
 

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return username;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
	
}
