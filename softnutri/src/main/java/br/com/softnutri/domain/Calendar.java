package br.com.softnutri.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calendar")
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCalendar;

	@ManyToOne
	private User professional;

	@ManyToOne
	private User patient;

	@ManyToOne
	private User receptionist;

	@Basic(optional = false)
	@Column(name = "dateOfDay", nullable = false)
	private LocalDate dateOfDay;

	@Basic(optional = false)
	@Column(name = "hourOfDay", nullable = false)
	private LocalTime hourOfDay;

	@Basic(optional = false)
	@Column(name = "note", nullable = false)
	private String note;

	@Basic(optional = false)
	@Column(name = "completed", nullable = false)
	private boolean completed;

	@Basic(optional = false)
	@Column(name = "cancel", nullable = false)
	private boolean cancel;

	public Long getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(Long idCalendar) {
		this.idCalendar = idCalendar;
	}

	public User getProfessional() {
		return professional;
	}

	public void setProfessional(User professional) {
		this.professional = professional;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public LocalDate getDateOfDay() {
		return dateOfDay;
	}

	public void setDateOfDay(LocalDate dateOfDay) {
		this.dateOfDay = dateOfDay;
	}

	public LocalTime getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(LocalTime hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(User receptionist) {
		this.receptionist = receptionist;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

}
