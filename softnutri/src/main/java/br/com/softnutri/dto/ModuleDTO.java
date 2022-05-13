package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Module;

public class ModuleDTO {
	private Long idModule;

	private String name;

	private String pathBase;

	private String icon;

	private Integer orders;

	public ModuleDTO() {
	}

	public ModuleDTO(Module module) {

		this.idModule = module.getIdModule();
		this.name = module.getName();
		this.pathBase = module.getPathBase();
		this.icon = module.getIcon();
		this.orders = module.getOrders();

	}

	public Long getIdModule() {
		return idModule;
	}

	public String getName() {
		return name;
	}

	public String getPathBase() {
		return pathBase;
	}

	public String getIcon() {
		return icon;
	}

	public Integer getOrders() {
		return orders;
	}

	public List<ModuleDTO> converter(List<Module> modules) {
		return modules.stream().map(ModuleDTO::new).toList();
	}

}
