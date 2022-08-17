package br.com.softnutri.enuns;

import java.util.EnumSet;
import java.util.List;

public enum ModuleAll {

	HOME(getHomePage(), "/home", getHomePage(), 1, getPapers(getHomePage().toUpperCase())),
	PERSON_MODULE(getPatient(), "/patient", "person", 2, getPapers(getPatient().toUpperCase())),
	FOOD_MODULE(getFood(), "/food", "fastfood", 3, getPapers(getFood().toUpperCase())),
	MENU_MODULE(getMenu(), "/menu", "restaurant_menu", 4, getPapers(getMenu().toUpperCase()));

	private String name;
	private String pathBase;
	private String icon;
	private Integer orders;
	private List<PaperAll> listPapers;
	private static final String HOME_PAGE = "home";
	private static final String PATIENT = "patient";
	private static final String FOOD = "food";
	private static final String MENU = "menu";

	private ModuleAll(String name, String pathBase, String icon, Integer orders, List<PaperAll> listPapers) {
		this.name = name;
		this.pathBase = pathBase;
		this.icon = icon;
		this.orders = orders;
		this.listPapers = listPapers;
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

	public static String getPatient() {
		return PATIENT;
	}

	public static String getFood() {
		return FOOD;
	}

	public static String getMenu() {
		return MENU;
	}

	public static String getHomePage() {
		return HOME_PAGE;
	}

	public List<PaperAll> getListPapers() {
		return listPapers;
	}

	private static EnumSet<PaperAll> listPapers() {
		return EnumSet.allOf(PaperAll.class);
	}

	private static List<PaperAll> getPapers(String name) {
		EnumSet<PaperAll> list = listPapers();
		return list.stream().filter(c -> c.name().contains(name.toUpperCase())).toList();
	}

}
