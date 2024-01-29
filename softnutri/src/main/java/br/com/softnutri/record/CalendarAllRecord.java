package br.com.softnutri.record;

import java.util.List;

import br.com.softnutri.dto.CalendarDTO;

public record CalendarAllRecord(List<CalendarDTO> calendar, List<CalendarEvent> calendarEvent) {

}
