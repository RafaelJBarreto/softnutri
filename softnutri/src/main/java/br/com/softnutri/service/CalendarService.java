package br.com.softnutri.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Calendar;
import br.com.softnutri.domain.Phone;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.record.CalendarEvent;
import br.com.softnutri.repository.CalendarRepository;
import br.com.softnutri.util.Criptografia;
import br.com.softnutri.util.Util;

@Service
public class CalendarService {
	
	private final CalendarRepository calendarRepository;

	@Autowired
	public CalendarService(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}
	
	public ResponseEntity<MessageResponse> save(Calendar calendar) throws SoftNutriException { 
		try {
			this.calendarRepository.save(calendar);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));

		} catch (Exception e) {
			throw new SoftNutriException("Error save Calendar ", e);
		}
	}
	
	public ResponseEntity<List<CalendarEvent>> listAll() throws SoftNutriException { 
		try {
			return ResponseEntity.ok(this.calendarRepository.findAll().stream().map(obj -> new CalendarEvent(obj.getIdCalendar(), Date.from(obj.getDateOfDay().atStartOfDay().atZone(ZoneId.systemDefault()) .toInstant()), 
					Util.convertHour(obj.getHourOfDay()), this.getTitle(obj), obj.isCancel(), obj.isCompleted(), null, true)).toList());
		} catch (Exception e) {
			throw new SoftNutriException("Error list all Calendar ", e);
		}
	}
	
	public ResponseEntity<MessageResponse> cancel(Long idCalendar) throws SoftNutriException {
		try {
				Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
				if (calendar.isPresent()) {
					Calendar c = calendar.get();
					c.setCancel(true);
					this.calendarRepository.save(c);
					return ResponseEntity.ok(new MessageResponse("CALENDAR.MSG_CANCEL"));
				} else {
					return ResponseEntity.ok(new MessageResponse("CALENDAR.CALENDAR_CANCEL_ERROR"));
				}
		} catch (Exception e) {
			throw new SoftNutriException("Error cancel Calendar ", e);
		}
	}
	
	public CalendarDTO getCalendar(Long idCalendar) throws SoftNutriException {
		try {
			Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
			if (calendar.isPresent()) {
				return CalendarDTO.converter(calendar.get());
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SoftNutriException("Error getCalendar ", e);
		}
	}
		
	public ResponseEntity<List<CalendarDTO>> findCalendarProfessional(long idProfessional) throws SoftNutriException { 
		try {
			return ResponseEntity.ok(CalendarDTO.converter(this.calendarRepository.findByProfessionalIdPersonAndDateOfDayAndCancelOrderByHourOfDayAsc(idProfessional, LocalDate.now(), false)));
		} catch (Exception e) {
			throw new SoftNutriException("Error findCalendarProfessional ", e);
		}
	}
	
	private String getTitle(Calendar calendar) {
		StringBuilder title = new StringBuilder();
		title.append(Criptografia.decode(calendar.getPatient().getName()) + " - " + Criptografia.decode(calendar.getPatient().getEmail()));
		for(Phone p: calendar.getPatient().getPhones()) {
			title.append(" - " + Criptografia.decode(p.getNumber()));
		}
		return title.toString();
	}
}
