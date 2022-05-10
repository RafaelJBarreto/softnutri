package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.BodyHistory;

@Repository
public interface BodyHistoryRepository extends JpaRepository<BodyHistory, Long> {

	List<BodyHistory> findByPersonIdPerson(Long idPessoa);

}
