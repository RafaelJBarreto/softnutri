package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.CompositionTable;

@Repository
public interface TableRepository extends JpaRepository<CompositionTable, Long>{

}
