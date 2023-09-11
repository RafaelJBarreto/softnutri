package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Snack;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long>{

}
