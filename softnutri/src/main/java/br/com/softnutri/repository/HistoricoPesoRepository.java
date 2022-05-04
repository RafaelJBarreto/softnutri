package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softnutri.dominio.HistoricoCorporal;

public interface HistoricoPesoRepository extends JpaRepository<HistoricoCorporal, Long> {

	List<HistoricoCorporal> findByPessoaIdPessoa(Long idPessoa);

}
