package br.com.softnutri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.repository.CalendarRepository;

@Service
public class CalendarService {
	
	private final CalendarRepository calendarRepository;

	@Autowired
	public CalendarService(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}
	
	public ResponseEntity<MessageResponse> save(CalendarDTO calendar) { 
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}
	
	public ResponseEntity<List<CalendarDTO>> listAll() { 
		return ResponseEntity.ok(CalendarDTO.converter(this.calendarRepository.findAll()));
	}
		 
}
