package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softnutri.dominio.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	List<Telefone> findByPessoaIdPessoa(Long idPessoa);

}
