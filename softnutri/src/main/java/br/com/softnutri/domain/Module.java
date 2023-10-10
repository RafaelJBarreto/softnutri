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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
