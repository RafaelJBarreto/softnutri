package br.com.softnutri.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "module")
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModule;

	@Column(name = "name", unique = true, nullable = false, length = 25)
	private String name;

	@Column(name = "pathBase", unique = true, length = 40, nullable = false)
	private String pathBase;

	@Lob
	@Column(name = "icon", nullable = true)
	private String icon;

	@Column(name = "orders", nullable = false)
	private Integer orders;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "module", fetch = FetchType.LAZY)
	private List<ModuleRole> moduleRole;

	public Module() {
	}

	public Module(Long idModule, String name, String pathBase, String icon, Integer orders) {
		super();
		this.idModule = idModule;
		this.name = name;
		this.pathBase = pathBase;
		this.icon = icon;
		this.orders = orders;
	}

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPathBase() {
		return pathBase;
	}

	public void setPathBase(String pathBase) {
		this.pathBase = pathBase;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public List<ModuleRole> getModuleRole() {
		return moduleRole;
	}

	public void setModuleRole(List<ModuleRole> moduleRole) {
		this.moduleRole = moduleRole;
	}

}
