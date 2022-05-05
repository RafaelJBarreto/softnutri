package br.com.softnutri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dominio.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);

	@Modifying
	int deleteByPessoa(Pessoa pessoa);
	
	Optional<RefreshToken>findByPessoa(Pessoa pessoa);
}
