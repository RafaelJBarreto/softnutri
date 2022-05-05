package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	List<Telefone> findByPessoaIdPessoa(Long idPessoa);

}
