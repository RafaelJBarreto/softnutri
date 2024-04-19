package br.com.softnutri.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	List<Calendar> findByProfessionalIdPersonAndDateOfDayAndCancelOrderByHourOfDayAsc(Long idPessoa, LocalDate now, boolean cancel);
	
	List<Calendar> findByCompletedAndCancel(boolean completed, boolean cancel);

}
