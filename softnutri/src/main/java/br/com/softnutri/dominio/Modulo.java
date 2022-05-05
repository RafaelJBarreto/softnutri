package br.com.softnutri.dominio;

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
import jakarta.xml.bind.annotation.XmlTransient;

 
@Entity
@Table(name = "modulo")
public class Modulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModulo;

	@Column(name = "nome", unique = true, nullable = false, length = 25)
	private String nome;

	@Column(name = "pathBase", unique = true, length = 40, nullable = false)
	private String pathBase;

	@Lob
	@Column(name = "icon", nullable = true)
	@XmlTransient
	private byte[] icon;

	@Column(name = "ordem", nullable = false)
	private Integer ordem;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "modulo", fetch = FetchType.LAZY)
	private List<ModuloPapel> moduloPapel;

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPathBase() {
		return pathBase;
	}

	public void setPathBase(String pathBase) {
		this.pathBase = pathBase;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public List<ModuloPapel> getModuloPapel() {
		return moduloPapel;
	}

	public void setModuloPapel(List<ModuloPapel> moduloPapel) {
		this.moduloPapel = moduloPapel;
	}
	
}
