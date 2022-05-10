package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

	List<Phone> findByPersonIdPerson(Long idPessoa);

}
