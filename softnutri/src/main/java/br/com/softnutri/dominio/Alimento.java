package br.com.softnutri.dominio;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimento")
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlimento;

	@Basic(optional = false)
	@Column(name = "descricao", nullable = false, length = 150)
	private String descricao;

	@Embedded
	private DadosNutricionais dadosNutricionais;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "alimento", fetch = FetchType.LAZY)
	private List<GrupoAlimento> grupoAlimento;

	public Alimento() {
	}

	public Alimento(String descricao, float calorias, float proteina, float lipideos, float carboidrato) {
		this.descricao = descricao;
		this.dadosNutricionais = new DadosNutricionais(calorias, proteina, lipideos, carboidrato);
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
