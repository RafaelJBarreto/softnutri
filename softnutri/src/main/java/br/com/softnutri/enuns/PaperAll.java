package br.com.softnutri.enuns;

public enum PaperAll {
		
	FOOD("get", "post", "put", "delete"),
	PATIENT("get", "post", "put", "delete"),
	PROFESSIONAL("get", "post", "put", "delete"),
	MENU("get", "post", "put", "delete"),
	PERMISSION("get", "post", "put", "delete"),
	CALENDAR("get", "post", "put", "delete"),
	TABLE("get", "post", "put", "delete"),
	SNACK("get", "post", "put", "delete"),
	HOME("get", null, null, null);
	
	private String get;
	private String post;
	private String put;
	private String delete;
	
	private PaperAll(String get, String post, String put, String delete) {
		this.get = get;
		this.post = post;
		this.put = put;
		this.delete = delete;
	}
	public String getGet() {
		return get;
	}
	public String getPost() {
		return post;
	}
	public String getPut() {
		return put;
	}
	public String getDelete() {
		return delete;
	}
	
	
}
