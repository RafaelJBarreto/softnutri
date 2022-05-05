package br.com.softnutri.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupoAlimento")
public class GrupoAlimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupoAlimento;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Grupo grupo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Alimento alimento;

	public Long getIdGrupoAlimento() {
		return idGrupoAlimento;
	}

	public void setIdGrupoAlimento(Long idGrupoAlimento) {
		this.idGrupoAlimento = idGrupoAlimento;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	
	

}
