package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
