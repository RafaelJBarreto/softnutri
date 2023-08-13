package br.com.softnutri.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "intermission")
public class Intermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIntermission;

	@Basic(optional = true)
	@Column(name = "time", nullable = true)
	private int time;

}