package br.com.softnutri.dominio;

import java.time.LocalDate;

import br.com.softnutri.util.Criptografia;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {

	@Basic(optional = false)
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Basic(optional = false)
	@Column(name = "idioma", nullable = false)
	private String idioma;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "tipoUsuario", nullable = false, length = 13)
	private TipoUsuario tipoUsuario;

	@Basic(optional = false)
	@Column(name = "dataCadastro", nullable = false)
	private LocalDate dataCadastro = LocalDate.now();

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.encoderSecurity(senha);
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}
