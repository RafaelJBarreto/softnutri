package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.dominio.Nutricionista;
import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dominio.Sexo;
import br.com.softnutri.dominio.TipoUsuario;
import br.com.softnutri.dominio.Usuario;
import br.com.softnutri.repository.PessoaRepository;
import br.com.softnutri.repository.UsuarioRepository;

@SpringBootTest
class SoftnutriApplicationTests {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void testaCadastroNutricionista() {

		Nutricionista p = new Nutricionista();
		p.setCpf("18956896533");
		p.setDataNascimento(LocalDate.now());
		p.setEmail("ana_eliza@outlook.com.br");
		p.setEndereco("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setNome("Ana Eliza");
		p.setSexo(Sexo.F);
		p.setSenha("123456");
		p.setTipoUsuario(TipoUsuario.NUTRICIONISTA);
		p.setCrn("123456789");
		p.setAnuidade(true);

		Usuario nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCadastroPessoa() {

		Pessoa p = new Pessoa();
		p.setCpf("10490376690");
		p.setDataNascimento(LocalDate.now());
		p.setEmail("rafaelbarreto@outlook.com.br");
		p.setEndereco("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setNome("Rafael");
		p.setSexo(Sexo.M);
		Pessoa pessoaCadastrada = pessoaRepository.save(p);

		assertEquals(p.getEmail(), pessoaCadastrada.getEmail());
	}

	@Test
	void testaCadastroRecepcao() {

		Usuario p = new Usuario();
		p.setCpf("18956896533");
		p.setDataNascimento(LocalDate.now());
		p.setEmail("picadura@outlook.com.br");
		p.setEndereco("Rua da preguiça, Barbacena-MG");
		p.setNome("Xuxu baitola");
		p.setSexo(Sexo.F);
		p.setSenha("123456");
		p.setTipoUsuario(TipoUsuario.RECEPCIONISTA);

		Usuario nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

}
