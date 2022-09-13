package br.com.softnutri.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.softnutri.domain.Calendar;

public class CalendarDTO {

	private Long idCalendar;

	private UserDTO professional;

	private UserDTO patient;

	private UserDTO receptionist;

	private LocalDate dateOfDay;

	private LocalTime hourOfDay;

	private String note;

	private boolean completed;

	private boolean cancel;
	

	public CalendarDTO(Calendar calendar) {
		this.idCalendar = calendar.getIdCalendar();
		this.professional = new UserDTO(calendar.getProfessional());
		this.patient  = new UserDTO(calendar.getPatient());
		this.receptionist  = new UserDTO(calendar.getReceptionist());
		this.dateOfDay = calendar.getDateOfDay();
		this.hourOfDay = calendar.getHourOfDay();
		this.note = calendar.getNote();
		this.completed = calendar.isCompleted();
		this.cancel = calendar.isCancel();
	}

	public Long getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(Long idCalendar) {
		this.idCalendar = idCalendar;
	}

	public UserDTO getProfessional() {
		return professional;
	}

	public void setProfessional(UserDTO professional) {
		this.professional = professional;
	}

	public UserDTO getPatient() {
		return patient;
	}

	public void setPatient(UserDTO patient) {
		this.patient = patient;
	}

	public UserDTO getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(UserDTO receptionist) {
		this.receptionist = receptionist;
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

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public static List<CalendarDTO> converter(List<Calendar> calendar) {
		return calendar.stream().map(CalendarDTO::new).toList();
	}
	
	
	
}
