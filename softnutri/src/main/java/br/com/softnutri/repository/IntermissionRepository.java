package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Intermission;

@Repository
public interface IntermissionRepository extends JpaRepository<Intermission, Long>{

}
