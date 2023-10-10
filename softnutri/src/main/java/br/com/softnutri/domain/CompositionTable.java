package br.com.softnutri.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "compositionTable")
public class CompositionTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompositionTable;
	
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 100)
	private String description;
}
