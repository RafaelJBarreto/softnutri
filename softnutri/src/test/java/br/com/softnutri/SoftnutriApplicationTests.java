package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.dominio.Modulo;
import br.com.softnutri.dominio.ModuloPapel;
import br.com.softnutri.dominio.Nutricionista;
import br.com.softnutri.dominio.Papel;
import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dominio.Sexo;
import br.com.softnutri.dominio.TipoUsuario;
import br.com.softnutri.dominio.Usuario;
import br.com.softnutri.repository.ModuloPapelRepository;
import br.com.softnutri.repository.ModuloRepository;
import br.com.softnutri.repository.PapelRepository;
import br.com.softnutri.repository.PessoaRepository;
import br.com.softnutri.repository.UsuarioRepository;
import br.com.softnutri.util.Criptografia;

@SpringBootTest
class SoftnutriApplicationTests {

	private final PessoaRepository pessoaRepository;

	private final UsuarioRepository usuarioRepository;

	private final PapelRepository papelRepository;
	
	private final ModuloRepository moduloRepository;
	
	private final ModuloPapelRepository moduloPapelRepository;

	@Autowired
	public SoftnutriApplicationTests(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository,
			PapelRepository papelRepository, ModuloRepository moduloRepository, ModuloPapelRepository moduloPapelRepository) {
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
		this.papelRepository = papelRepository;
		this.moduloRepository = moduloRepository;
		this.moduloPapelRepository = moduloPapelRepository;
	}

	@Test
	void testaCadastroNutricionista() {

		Nutricionista p = new Nutricionista();
		p.setCpf("18956896533");
		p.setDataNascimento(LocalDate.now());
		p.setEmail("ana_eliza1@outlook.com.br");
		p.setEndereco("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setNome("Ana Eliza");
		p.setSexo(Sexo.F);
		p.setSenha("123456");
		p.setTipoUsuario(TipoUsuario.NUTRICIONISTA);
		p.setCrn("123456789");
		p.setAnuidade(true);
		p.setIdioma("pt-Br");

		Usuario nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCadastroPessoa() {

		Pessoa p = new Pessoa();
		p.setCpf("10490376690");
		p.setDataNascimento(LocalDate.now());
		p.setEmail("rola@outlook.com.br");
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
		p.setEmail("picadura1@outlook.com.br");
		p.setEndereco("Rua da preguiça, Barbacena-MG");
		p.setNome("Xuxu baitola");
		p.setSexo(Sexo.F);
		p.setSenha("123456");
		p.setTipoUsuario(TipoUsuario.RECEPCIONISTA);
		p.setIdioma("pt-Br");

		Usuario nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCriarPapel() {

		Papel p = new Papel();
		p.setDescricao("Usuario");

		Papel nc = papelRepository.save(p);

		assertNotNull(nc.getIdPapel());

	}

	@Test
	void testaCriarModulo() {

		Modulo m = new Modulo();
		m.setNome("Cadastro");
		m.setOrdem(1);
		m.setPathBase("app/cadastro");
		m.setIcon(null);

		Modulo nc = moduloRepository.save(m);

		assertNotNull(nc.getIdModulo());

	}

	@Test
	void testaAssociacaoPapelModulo() {

		ModuloPapel mp = new ModuloPapel();
		mp.setModulo(moduloRepository.findById(1L).get());
		mp.setPapel(papelRepository.findById(1L).get());
		
		ModuloPapel nc = moduloPapelRepository.save(mp);

		assertNotNull(nc.getIdModuloPapel());

	}
	
	@Test
	void testaAssociacaoUsuarioPapel() {

		Usuario u = usuarioRepository.findByEmail(Criptografia.encode("ana_eliza1@outlook.com.br")).get();
		 Set<Papel> papel = new HashSet<Papel>() ;
		 papel.add(papelRepository.findById(1L).get());
		u.setPapel(papel);
		
		Usuario nc = usuarioRepository.save(u);

		assertNotNull(nc.getPapel());

	}
	
	

}
