package br.com.softnutri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findById(Long id);
	
	Optional<Pessoa> findByEmail(String email);

	List<Pessoa> findByNome(String nome);

}
