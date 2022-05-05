package br.com.softnutri.dto;

import java.util.Date;

public class AuthToken {

    private Long idUser;
    private String token;
    private String requestRefreshToken;
    private Date expiration;
    
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRequestRefreshToken() {
		return requestRefreshToken;
	}
	public void setRequestRefreshToken(String requestRefreshToken) {
		this.requestRefreshToken = requestRefreshToken;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

}
