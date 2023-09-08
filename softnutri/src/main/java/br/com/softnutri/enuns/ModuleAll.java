package br.com.softnutri.enuns;

import java.util.EnumSet;
import java.util.List;

public enum ModuleAll {

	HOME(getHomePage(), "/home", "fa-solid fa-house-user", 1, getPapers(getHomePage().toUpperCase())),
	PERSON_MODULE(getPatient(), "/patient", "fa-solid fa-user-nurse", 2, getPapers(getPatient().toUpperCase())),
	PROFESSIONAL_MODULE(getProfessional(), "/professional", "fa-solid fa-user", 3, getPapers(getProfessional().toUpperCase())),
	FOOD_MODULE(getFood(), "/food", "fa-solid fa-apple-whole", 4, getPapers(getFood().toUpperCase())),
	MENU_MODULE(getMenu(), "/menu", "fa-solid fa-utensils", 5, getPapers(getMenu().toUpperCase())),
	PERMISSION_MODULE(getPermission(), "/permission", "fa-solid fa-screwdriver-wrench", 5, getPapers(getPermission().toUpperCase())),
	CALENDAR_MODULE(getCalendar(), "/calendar", "fa-solid fa-calendar-days", 6, getPapers(getCalendar().toUpperCase())),
	TABLE_MODULE(getTable(), "/table", "fa-solid fa-table-list", 7, getPapers(getTable().toUpperCase()));

	private String name;
	private String pathBase;
	private String icon;
	private Integer orders;
	private List<PaperAll> listPapers;
	private static final String HOME_PAGE = "home";
	private static final String PATIENT = "patient";
	private static final String FOOD = "food";
	private static final String MENU = "menu";
	private static final String PROFESSIONAL = "professional";
	private static final String PERMISSION = "permission";
	private static final String CALENDAR = "calendar";
	private static final String TABLE = "table";

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
	
	public static String getProfessional() {
		return PROFESSIONAL;
	}
	
	public static String getPermission() {
		return PERMISSION;
	}

	public static String getCalendar() {
		return CALENDAR;
	}
	
	public static String getTable() {
		return TABLE;
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
