package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
	
	Module findByName(String name);
	
	@Query(value = "SELECT distinct m.* FROM module_role mr join module m on mr.id_module = m.id_module join person_paper pp on pp.id_paper = mr.id_paper WHERE pp.id_person = :idPerson order by m.orders", nativeQuery=true)
	List<Module> findModuleByIdPessoa(@Param("idPerson") Long idPerson);

}
