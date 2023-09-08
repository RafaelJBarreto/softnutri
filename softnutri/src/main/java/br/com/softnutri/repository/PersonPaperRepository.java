package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.PersonPaper;

@Repository
public interface PersonPaperRepository extends JpaRepository<PersonPaper, Long> {
	
	List<PersonPaper> findByUserIdPerson(@Param("idPerson") Long idPerson);
	
}
