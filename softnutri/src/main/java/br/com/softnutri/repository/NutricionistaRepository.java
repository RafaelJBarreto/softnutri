package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}
