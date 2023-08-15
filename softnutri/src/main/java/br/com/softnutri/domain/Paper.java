package br.com.softnutri.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paper")
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaper;

	@Column(name = "description", unique = true, nullable = false, length = 50)
	private String description;
	
	@Column(name = "access", length = 3)
	private String get;

	@Column(name = "send", length = 4)
	protected String post;

	@Column(name = "put", length = 3)
	private String put;

	@Column(name = "remove", length = 6)
	private String delete;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "paper", fetch = FetchType.LAZY)
	private List<ModuleRole> modulesRoles;
	
	@OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonPaper> personPaper;

	public Paper() {
	}
	
	public Paper(Long idPaper) {
		this.idPaper = idPaper;
	}

	public Paper(Long idPaper, String description, String get, String post, String put, String delete) {
		this.idPaper = idPaper;
		this.description = description;
		this.get = get;
		this.post = post;
		this.put = put;
		this.delete = delete;
	}
	
}
