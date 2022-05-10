package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Paper;

@Repository
public interface PaperRepository  extends JpaRepository<Paper, Long>{

	Paper findByDescription(String descricao);

}
