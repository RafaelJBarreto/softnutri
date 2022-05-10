package br.com.softnutri.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nutritionist")
public class Nutritionist extends User {

	@Basic(optional = false)
	@Column(name = "crn", nullable = false)
	private String crn;
	private boolean annuity;

	public Nutritionist() {
		super();
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public boolean isAnnuity() {
		return annuity;
	}

	public void setAnnuity(boolean annuity) {
		this.annuity = annuity;
	}


}
