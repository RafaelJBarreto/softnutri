package br.com.softnutri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
		
	Optional<Person> findByEmail(String email);

	List<Person> findByName(String nome);

}
