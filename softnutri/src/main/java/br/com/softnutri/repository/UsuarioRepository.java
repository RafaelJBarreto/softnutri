package br.com.softnutri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.dominio.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	@Query(value = "SELECT u.idioma FROM usuario u join pessoa p on p.idPessoa = u.idPessoa WHERE p.idPessoa = :idPessoa", nativeQuery=true)
	String findIdiomaByIdPessoa(@Param("idPessoa") Long idPessoa);
	
	@Query(value = "SELECT u.senha FROM usuario u join pessoa p on p.idPessoa = u.idPessoa WHERE p.idPessoa = :idPessoa", nativeQuery=true)
	String getSenhaByIdPessoa(@Param("idPessoa") Long idPessoa);
}
