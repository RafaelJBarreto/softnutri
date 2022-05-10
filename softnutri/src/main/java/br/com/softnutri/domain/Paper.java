package br.com.softnutri.domain;

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
@Table(name = "paper")
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaper;

	@Column(name = "description", unique = true, nullable = false, length = 50)
	private String description;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "paper", fetch = FetchType.LAZY)
	private List<ModuleRole> modulesRoles;

	public Long getIdPaper() {
		return idPaper;
	}

	public void setIdPaper(Long idPaper) {
		this.idPaper = idPaper;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ModuleRole> getModulesRoles() {
		return modulesRoles;
	}

	public void setModulesRoles(List<ModuleRole> modulesRoles) {
		this.modulesRoles = modulesRoles;
	}

}
