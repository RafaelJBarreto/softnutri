package br.com.softnutri.dominio;

import java.util.List;
 

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
@Table(name = "papel")
public class Papel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPapel;
	
	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "papel", fetch = FetchType.LAZY)
	private List<ModuloPapel> modulesRoles;

	public Long getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Long idPapel) {
		this.idPapel = idPapel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ModuloPapel> getModulesRoles() {
		return modulesRoles;
	}

	public void setModulesRoles(List<ModuloPapel> modulesRoles) {
		this.modulesRoles = modulesRoles;
	}
	
	
}
