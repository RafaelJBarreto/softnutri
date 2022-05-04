package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.ModuloPapel;

@Repository
public interface ModuloPapelRepository extends JpaRepository<ModuloPapel, Long>{

}
