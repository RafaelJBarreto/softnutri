package br.com.softnutri.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.service.ProcessCalendar;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

	@Autowired
	private ProcessCalendar service;
	
	@Scheduled(cron = "@daily")
	public void execute(){
		try {
			service.process();			
		} catch (Exception e) {
			throw new SoftNutriException("Error process calendar: ", e);
		}
		
	}
}
