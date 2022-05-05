package br.com.softnutri.dto;

public class UsuarioDTO {

	private Long idUsuario;
	private String email;
	private String senha;
	private String nome;
	private String idioma;
	private PapelDTO papel;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public PapelDTO getPapel() {
		return papel;
	}
	public void setPapel(PapelDTO papel) {
		this.papel = papel;
	}
	
}
