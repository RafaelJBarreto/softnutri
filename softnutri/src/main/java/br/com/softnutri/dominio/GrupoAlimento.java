package br.com.softnutri.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupoAlimento")
public class GrupoAlimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupoAlimento;

	@Basic(optional = false)
	@Column(name = "nome", nullable = false, length = 100)
	private String grupo;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "grupoAlimento", fetch = FetchType.LAZY)
	private List<Alimento> alimento = new ArrayList<>();

	public Long getIdGrupoAlimento() {
		return idGrupoAlimento;
	}

	public void setIdGrupoAlimento(Long idGrupoAlimento) {
		this.idGrupoAlimento = idGrupoAlimento;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<Alimento> getAlimento() {
		return alimento;
	}

}
