package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.HistoricoCorporal;

@Repository
public interface HistoricoPesoRepository extends JpaRepository<HistoricoCorporal, Long> {

	List<HistoricoCorporal> findByPessoaIdPessoa(Long idPessoa);

}
