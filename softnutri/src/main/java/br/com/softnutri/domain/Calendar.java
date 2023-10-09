package br.com.softnutri.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.softnutri.dto.CalendarDTO;
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
import lombok.Data;

@Data
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

	public Calendar() {

	}
	
	public Calendar (CalendarDTO dto, Long idReceptionist) {
		this.idCalendar = dto.getIdCalendar();
		this.cancel = dto.isCancel();
		this.dateOfDay = dto.getDateOfDay();
		this.hourOfDay = LocalTime.of(dto.getHourOfDayAux().getHour(), dto.getHourOfDayAux().getMinute());
		this.note = dto.getNote();
		this.patient = new User(dto.getPatient().getIdPerson());
		this.professional = new User(dto.getProfessional().getIdPerson());
		this.receptionist = new User(idReceptionist);
	}



}
