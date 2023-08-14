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
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.service.AutenticationService;
import br.com.softnutri.service.CalendarService;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CalendarController {
	
	private final CalendarService calendarService;
	private final AutenticationService autenticationService;
	

	@Autowired
	public CalendarController(CalendarService calendarService, AutenticationService autenticationService) {
		this.calendarService = calendarService;
		this.autenticationService = autenticationService;
	}

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody CalendarDTO dto) {
		return calendarService.save(CalendarDTO.converterToDomain(dto, autenticationService));
	}

	@GetMapping("/")
	public ResponseEntity<List<CalendarDTO>> findAll(){
		return this.calendarService.listAll(); 
	}
	
	@GetMapping(value = "/cancel/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable(value = "id") Long id) {
		return calendarService.cancel(id);
	}
	
	@GetMapping(value = "/get/{id}")
	public CalendarDTO getCalendar(@PathVariable(value = "id") Long id) {
		return calendarService.getCalendar(id);
	}
	
	@GetMapping("/professional")
	public ResponseEntity<List<CalendarDTO>> findCalendarProfessional(){
		return this.calendarService.findCalendarProfessional(getProfessional().getIdPerson()); 
	}
	
	private User getProfessional() {
		return autenticationService.getUserLogged();
	}
	
}
