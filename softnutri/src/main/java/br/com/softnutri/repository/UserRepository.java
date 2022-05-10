package br.com.softnutri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	@Query(value = "SELECT u.language FROM user u join person p on p.id_person = u.id_person WHERE p.id_person = :idPerson", nativeQuery=true)
	String findIdiomaByIdPessoa(@Param("idPerson") Long idPerson);
	
	@Query(value = "SELECT u.password FROM user u join person p on p.id_person = u.id_person WHERE p.id_person = :idPerson", nativeQuery=true)
	String getSenhaByIdPessoa(@Param("idPerson") Long idPerson);
}
