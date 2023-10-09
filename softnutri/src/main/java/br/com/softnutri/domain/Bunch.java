package br.com.softnutri.domain;

import java.util.List;

import br.com.softnutri.dto.BunchDTO;
import jakarta.persistence.Basic;
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
@Table(name = "bunch")
public class Bunch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBunch;

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bunch", fetch = FetchType.LAZY)
	private List<FoodBunch> foodBunch;

	public Bunch() {

	}
	
	public Bunch(BunchDTO groupDTO) {
		this.idBunch = groupDTO.getIdBunch();
		this.description = groupDTO.getDescription();
	}
}
