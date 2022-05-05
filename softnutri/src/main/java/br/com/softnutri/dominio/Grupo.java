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
@Table(name = "grupo")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupo;

	@Basic(optional = false)
	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "grupo", fetch = FetchType.LAZY)
	private List<GrupoAlimento> grupoAlimento = new ArrayList<>();

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<GrupoAlimento> getGrupoAlimento() {
		return grupoAlimento;
	}

	public void setGrupoAlimento(List<GrupoAlimento> grupoAlimento) {
		this.grupoAlimento = grupoAlimento;
	}

}
