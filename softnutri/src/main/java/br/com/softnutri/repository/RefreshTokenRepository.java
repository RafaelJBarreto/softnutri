package br.com.softnutri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.RefreshToken;
import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);

	@Transactional
	@Modifying
	int deleteByPerson(Person pessoa);
	
	Optional<RefreshToken>findByPerson(Person pessoa);
}
