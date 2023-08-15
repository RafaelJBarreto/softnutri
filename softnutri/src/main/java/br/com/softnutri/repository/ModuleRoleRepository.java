package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.ModuleRole;

@Repository
public interface ModuleRoleRepository extends JpaRepository<ModuleRole, Long>{
	
	@Query(value = "SELECT mr.* FROM module_role mr left join module m on mr.id_module = m.id_module left join person_paper pp on pp.id_paper = mr.id_paper WHERE pp.id_person = :idPerson", nativeQuery=true)
	List<ModuleRole> getPermissionPerson(@Param("idPerson") Long idPerson);
	
	@Query(value = "SELECT mr.* FROM module_role mr WHERE mr.id_paper not in :idPaper", nativeQuery=true)
	List<ModuleRole> getNotPermissionPerson(@Param("idPaper") List<Long> idPaper);

}
