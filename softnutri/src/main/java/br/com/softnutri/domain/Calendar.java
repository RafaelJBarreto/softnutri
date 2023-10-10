package br.com.softnutri.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "calendar")
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCalendar;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private User professional;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private User patient;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private User receptionist;

	@Basic(optional = false)
	@Column(name = "dateOfDay", nullable = false)
	private LocalDate dateOfDay;

	@Basic(optional = false)
	@Column(name = "hourOfDay", nullable = false)
	private LocalTime hourOfDay;

	private String note;

	@Basic(optional = false)
	@Column(name = "completed", nullable = false)
	private boolean completed;

	@Basic(optional = false)
	@Column(name = "cancel", nullable = false)
	private boolean cancel;
}
