package br.com.softnutri.config.security.payload.response;

import java.util.Date;
import java.util.List;

import br.com.softnutri.dto.ModuleDTO;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private String language;
	private Date expiration;
	private List<String> roles;
	private List<ModuleDTO> modules;

	public JwtResponse(String token, String type, String refreshToken, String language, Date expiration,
			List<String> roles, List<ModuleDTO> modules) {
		this.token = token;
		this.type = type;
		this.refreshToken = refreshToken;
		this.language = language;
		this.expiration = expiration;
		this.roles = roles;
		this.modules = modules;
	}

	public JwtResponse() {
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<ModuleDTO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}

}
