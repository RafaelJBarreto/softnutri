package br.com.softnutri.dominio;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nutricionista")
public class Nutricionista extends Usuario {

	@Basic(optional = false)
	@Column(name = "crn", nullable = false, length = 10)
	private String crn;
	private boolean anuidade;

	public Nutricionista() {
		super();
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public boolean isAnuidade() {
		return anuidade;
	}

	public void setAnuidade(boolean anuidade) {
		this.anuidade = anuidade;
	}

}
