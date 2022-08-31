package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Bunch;

@Repository
public interface BunchRepository extends JpaRepository<Bunch, Long>{

}
