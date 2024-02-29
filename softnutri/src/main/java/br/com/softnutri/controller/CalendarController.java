package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.records.CalendarAll;
import br.com.softnutri.service.CalendarService;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CalendarController {
	
	private final CalendarService calendarService;
	
	@Autowired
	public CalendarController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody CalendarDTO dto) {
		this.calendarService.save(dto);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}

	@GetMapping("/")
	public ResponseEntity<CalendarAll> findAll(){
		return ResponseEntity.ok(this.calendarService.listAll()); 
	}
	
	@GetMapping(value = "/cancel/{id}")
	public ResponseEntity<MessageResponse> cancel(@PathVariable(value = "id") Long id) {
		calendarService.cancel(id);
		return ResponseEntity.ok(new MessageResponse("CALENDAR.MSG_CANCEL"));
	}
	
	@GetMapping(value = "/get/{id}")
	public CalendarDTO getCalendar(@PathVariable(value = "id") Long id) {
		return calendarService.getCalendar(id);
	}
	
	@GetMapping("/professional")
	public ResponseEntity<List<CalendarDTO>> findCalendarProfessional(){
		return ResponseEntity.ok(this.calendarService.findCalendarProfessional()); 
	}
}
