package br.com.softnutri.dominio;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "moduloPapel")
public class ModuloPapel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModuloPapel;

	@JoinColumn(name = "idPapel", referencedColumnName = "idPapel", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Papel papel;

	@JoinColumn(name = "idModulo", referencedColumnName = "idModulo", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Modulo modulo;

	public Long getIdModuloPapel() {
		return idModuloPapel;
	}

	public void setIdModuloPapel(Long idModuloPapel) {
		this.idModuloPapel = idModuloPapel;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
}
