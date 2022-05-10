package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{

}
