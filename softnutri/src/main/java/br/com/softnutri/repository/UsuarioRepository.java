package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softnutri.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
