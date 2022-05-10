package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.ModuleRole;

@Repository
public interface ModuleRoleRepository extends JpaRepository<ModuleRole, Long>{

}
