package br.com.softnutri.domain;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "menuPerson")
public class MenuPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenuPerson;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Food food;

	@Column(nullable = false)
	private float amount;

	@Embedded
	private NutritionalData nutritionalData;

	@Builder.Default
	private LocalDateTime dateTimeRegistration = LocalDateTime.now();

}
