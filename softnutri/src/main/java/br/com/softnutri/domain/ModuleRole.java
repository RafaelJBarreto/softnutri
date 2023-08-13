package br.com.softnutri.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "moduleRole")
public class ModuleRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModuleRole;

	@JoinColumn(name = "idPaper", referencedColumnName = "idPaper", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Paper paper;

	@JoinColumn(name = "idModule", referencedColumnName = "idModule", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Module module;

	public ModuleRole() {

	}

	public ModuleRole(Paper paper, Module module) {
		super();
		this.paper = paper;
		this.module = module;
	}

}
