package br.com.softnutri.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Calendar;
import br.com.softnutri.repository.CalendarRepository;

@Service
public class ProcessCalendar {

	@Autowired
	private CalendarRepository calendarRepository;

	public void process() {
		final List<Calendar> calendars = calendarRepository.findByCompletedAndCancel(false, false);
		calendars.forEach(this::setCancel);
	}

	private void setCancel(Calendar calendar) {

		final LocalDateTime date = calendar.getDateOfDay().atTime(calendar.getHourOfDay());
		final long diff = ChronoUnit.DAYS.between(date, LocalDateTime.now());

		if (diff <= 0) {
			return;
		}
		calendar.setCancel(true);
		calendar.setNote("CALENDAR.CALENDAR_CANCEL_AUTOMATICALLY");
		calendarRepository.save(calendar);

	}

}
