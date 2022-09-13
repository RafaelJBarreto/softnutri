package br.com.softnutri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.CalendarDTO;
import br.com.softnutri.service.CalendarService;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveData(@RequestBody CalendarDTO dto) {
		return this.calendarService.save(dto);
	}

	@GetMapping("/")
	public ResponseEntity<List<CalendarDTO>> findAll(){
		return this.calendarService.listAll(); 
	}
	
}
