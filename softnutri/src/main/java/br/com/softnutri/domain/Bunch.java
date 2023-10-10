package br.com.softnutri.domain;

import java.util.List;

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

}
