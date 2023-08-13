package br.com.softnutri.dto;

import java.util.List;

import br.com.softnutri.domain.Module;
import lombok.Data;

@Data
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

	public static List<ModuleDTO> converter(List<Module> modules) {
		return modules.stream().map(ModuleDTO::new).toList();
	}

}
