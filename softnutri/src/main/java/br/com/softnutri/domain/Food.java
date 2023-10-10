package br.com.softnutri.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFood;

	@Column(name = "description", nullable = false, length = 150)
	private String description;
	
	@Column(name = "descriptionPreparation", length = 150)
	private String descriptionPreparation;

	@Embedded
	private NutritionalData nutritionalData;
	
	@JoinColumn(name = "idCompositionTable", referencedColumnName = "idCompositionTable", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private CompositionTable compositionTable;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "food", fetch = FetchType.LAZY)
	private List<MenuPerson> menuPerson;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "food", fetch = FetchType.EAGER)
	private List<FoodBunch> foodBunch;

}
