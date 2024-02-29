package br.com.softnutri.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Calendar;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.records.CalendarAll;
import br.com.softnutri.records.CalendarEvent;
import br.com.softnutri.repository.CalendarRepository;
import br.com.softnutri.util.Criptografia;
import br.com.softnutri.util.Util;

@Service
public class CalendarService {
	
	private final CalendarRepository calendarRepository;
	private final AutenticationService autenticationService;

	@Autowired
	public CalendarService(CalendarRepository calendarRepository, AutenticationService autenticationService) {
		this.calendarRepository = calendarRepository;
		this.autenticationService = autenticationService;
	}
	
	public void save(CalendarDTO dto) { 
		try {
			this.calendarRepository.save(Calendar.builder().idCalendar(dto.getIdCalendar()).cancel(dto.isCancel()).dateOfDay(dto.getDateOfDay()).
					hourOfDay(LocalTime.of(dto.getHourOfDayAux().getHour(), dto.getHourOfDayAux().getMinute())).note(dto.getNote()).
					patient(User.builder().idPerson(dto.getPatient().getIdPerson()).build()).
					professional(User.builder().idPerson(dto.getProfessional().getIdPerson()).build()).
					receptionist(User.builder().idPerson(autenticationService.getUserLogged().getIdPerson()).build()).build());
		} catch (Exception e) {
			throw new SoftNutriException("PATIENT.ERROR_SAVE_PATIENT", e);
		}
	}
	
	public CalendarAll listAll() { 
		try {
			final List<Calendar> calendarAll = this.calendarRepository.findAll();			
			return new CalendarAll(CalendarDTO.converter(calendarAll), calendarAll.stream().map(obj -> new CalendarEvent(obj.getIdCalendar(), Date.from(obj.getDateOfDay().atStartOfDay().atZone(ZoneId.systemDefault()) .toInstant()), 
					Util.convertHour(obj.getHourOfDay()), this.getTitle(obj), obj.isCancel(), obj.isCompleted(), null, true)).toList());
		} catch (Exception e) {
			throw new SoftNutriException("CALENDAR.ERROR_LIST_CALENDAR", e);
		}
	}
	
	public void cancel(Long idCalendar) {
		try {
				final Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
				calendar.ifPresent(c -> {
					c.setCancel(true);
					this.calendarRepository.save(c);
				});
		} catch (Exception e) {
			throw new SoftNutriException("CALENDAR.CALENDAR_CANCEL_ERROR", e);
		}
	}
	
	public CalendarDTO getCalendar(Long idCalendar) {
		try {
			final Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
			if (calendar.isPresent()) {
				return CalendarDTO.converter(calendar.get());
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SoftNutriException("CALENDAR.ERROR_GET_CALENDAR", e);
		}
	}
		
	public List<CalendarDTO> findCalendarProfessional() { 
		try {
			return CalendarDTO.converter(this.calendarRepository.findByProfessionalIdPersonAndDateOfDayAndCancelOrderByHourOfDayAsc(autenticationService.getUserLogged().getIdPerson(), LocalDate.now(), false));
		} catch (Exception e) {
			throw new SoftNutriException("CALENDAR.ERROR_LIST_CALENDAR", e);
		}
	}
	
	private String getTitle(Calendar calendar) {
		final StringBuilder title = new StringBuilder();
		title.append(Criptografia.decode(calendar.getPatient().getName()) + " - " + Criptografia.decode(calendar.getPatient().getEmail()));
		calendar.getPatient().getPhones().forEach(p -> 
			title.append(" - " + Criptografia.decode(p.getNumber()))
		);
		return title.toString();
	}
}
