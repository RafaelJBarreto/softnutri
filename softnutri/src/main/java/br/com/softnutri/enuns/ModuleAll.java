package br.com.softnutri.enuns;

public enum ModuleAll {

	PERSON_MODULE("list_person", "/person", "person", 1), 
	FOOD_MODULE("list_food", "/food", "fastfood", 2),
	MENU_MODULE("list_menu", "/menu", "restaurant_menu", 3);

	private String name;
	private String pathBase;
	private String icon;
	private Integer orders;

	private ModuleAll(String name, String pathBase, String icon, Integer orders) {
		this.name = name;
		this.pathBase = pathBase;
		this.icon = icon;
		this.orders = orders;
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
}
