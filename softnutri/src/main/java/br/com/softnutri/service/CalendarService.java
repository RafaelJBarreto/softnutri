package br.com.softnutri.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.domain.Calendar;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.repository.CalendarRepository;

@Service
public class CalendarService {
	
	private final CalendarRepository calendarRepository;

	@Autowired
	public CalendarService(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}
	
	public ResponseEntity<MessageResponse> save(Calendar calendar) { 
		try {
			this.calendarRepository.save(calendar);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));

		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		}
	}
	
	public ResponseEntity<List<CalendarDTO>> listAll() { 
		return ResponseEntity.ok(CalendarDTO.converter(this.calendarRepository.findAll()));
	}
	
	public ResponseEntity<MessageResponse> cancel(Long idCalendar) {
		Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
		if (calendar.isPresent()) {
			Calendar c = calendar.get();
			c.setCancel(true);
			this.calendarRepository.save(c);
			return ResponseEntity.ok(new MessageResponse("CALENDAR.MSG_CANCEL"));
		} else {
			return null;
		}
	}
	
	public CalendarDTO getCalendar(Long idCalendar) {
		Optional<Calendar> calendar = this.calendarRepository.findById(idCalendar);
		if (calendar.isPresent()) {
			return CalendarDTO.converter(calendar.get());
		} else {
			return null;
		}
	}
		
	public ResponseEntity<List<CalendarDTO>> findCalendarProfessional(long idProfessional) { 
		return ResponseEntity.ok(CalendarDTO.converter(this.calendarRepository.findByProfessionalIdPersonAndDateOfDayAndCancelOrderByHourOfDayAsc(idProfessional, LocalDate.now(), false)));
	}
}
