package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.interfaces.PermissionReturn;

@Repository
public interface ModuleRoleRepository extends JpaRepository<ModuleRole, Long>{
	
	@Query(value = "SELECT p.id_paper as idPaper, p.description, p.access, p.send, p.put as alterar, p.remove, pp.access as register, pp.send as post, pp.put as put, pp.remove as deletar, m.id_module as idModule, m.name FROM module_role mr left join module m on mr.id_module = m.id_module left join paper p on p.id_paper = mr.id_paper left join person_paper pp on pp.paper_id_paper = mr.id_paper WHERE pp.user_id_person = :idPerson", nativeQuery=true)
	List<PermissionReturn> getPermissionPerson(@Param("idPerson") Long idPerson);
	
	@Query(value = "SELECT p.id_paper as idPaper, p.description, p.access, p.send, p.put, p.remove,  m.id_module as idModule, m.name  FROM module_role mr left join module m on mr.id_module = m.id_module left join paper p on p.id_paper = mr.id_paper left join person_paper pp on pp.paper_id_paper = mr.id_paper WHERE p.id_paper not in :idPaper", nativeQuery=true)
	List<PermissionReturn> getNotPermissionPerson(@Param("idPaper") List<Long> idPaper);

}