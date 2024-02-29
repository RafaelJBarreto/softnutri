package br.com.softnutri.records;

import java.util.List;

import br.com.softnutri.dto.CalendarDTO;

public record CalendarAll(List<CalendarDTO> calendar, List<CalendarEvent> calendarEvent) {}
