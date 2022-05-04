package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softnutri.dominio.Nutricionista;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}
