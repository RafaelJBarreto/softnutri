package br.com.softnutri.dto;

public class UserDTO {

	private Long idUser;
	private String email;
	private String password;
	private String name;
	private String language;
	private PaperDTO paper;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
