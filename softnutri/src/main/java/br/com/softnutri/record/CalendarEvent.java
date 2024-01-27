package br.com.softnutri.record;

import java.util.Date;

public record CalendarEvent(Long id, Date start, float end, String title, boolean cancel, boolean completed, EventColor color, boolean draggable) {
		
	private static final String COLORTEXT = "#000000";
	
	public CalendarEvent{
		if(cancel) {
			color = new EventColor("#ad2121", "#FAE3E3", COLORTEXT) ;
		}else if(completed) {
			color = new EventColor("#008000", "#b7edb7", COLORTEXT) ;
		}else {
			color = new EventColor("#e3bc08", "#FDF1BA", COLORTEXT) ;
		}
	}
	
		
}
