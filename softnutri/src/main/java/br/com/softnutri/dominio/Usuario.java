package br.com.softnutri.dominio;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Basic(optional = false)
	@Column(name = "senha", nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "tipoUsuario", nullable = false, length = 13)
	private TipoUsuario tipoUsuario;

	@Basic(optional = false)
	@Column(name = "dataCadastro", nullable = false)
	private LocalDate dataCadastro = LocalDate.now();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarioPapel",
            joinColumns = {
            @JoinColumn(name = "idUsuario")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "idPapel") })
    private Set<Papel> papel;
    
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Set<Papel> getPapel() {
		return papel;
	}

	public void setPapel(Set<Papel> papel) {
		this.papel = papel;
	}

}
