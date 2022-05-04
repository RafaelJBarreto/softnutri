package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{

}
