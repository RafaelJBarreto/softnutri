package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Papel;

@Repository
public interface PapelRepository  extends JpaRepository<Papel, Long>{

	Papel findByDescricao(String descricao);

}
