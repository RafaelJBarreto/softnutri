package br.com.softnutri.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.softnutri.domain.Calendar;
import lombok.Data;

@Data
public class CalendarDTO {

	private Long idCalendar;
	private UserDTO professional;
	private UserDTO patient;
	private UserDTO receptionist;
	private LocalDate dateOfDay;
	private LocalTime hourOfDay;
	private LocalDateTime hourOfDayAux;
	private String note;
	private boolean completed;
	private boolean cancel;

	public CalendarDTO() {
	}

	public CalendarDTO(Calendar calendar) {
		this.idCalendar = calendar.getIdCalendar();
		this.professional = new UserDTO(calendar.getProfessional());
		this.patient = new UserDTO(calendar.getPatient());
		this.receptionist = new UserDTO(calendar.getReceptionist());
		this.dateOfDay = calendar.getDateOfDay();
		this.hourOfDay = calendar.getHourOfDay();
		this.note = calendar.getNote();
		this.completed = calendar.isCompleted();
		this.cancel = calendar.isCancel();
	}

	
	public static CalendarDTO converter(Calendar calendar) {
		return new CalendarDTO(calendar);
	}
	
	public static List<CalendarDTO> converter(List<Calendar> calendar) {
		return calendar.stream().map(CalendarDTO::new).toList();
	}
}
