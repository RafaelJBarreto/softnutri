package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.domain.Nutritionist;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.Gender;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.PaperRepository;
import br.com.softnutri.repository.PersonRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

@SpringBootTest
class SoftnutriApplicationTests {

	private final PersonRepository pessoaRepository;

	private final UserRepository usuarioRepository;

	private final PaperRepository papelRepository;
	
	private final ModuleRepository moduloRepository;
	
	private final ModuleRoleRepository moduloPapelRepository;

	@Autowired
	public SoftnutriApplicationTests(PersonRepository pessoaRepository, UserRepository usuarioRepository,
			PaperRepository papelRepository, ModuleRepository moduloRepository, ModuleRoleRepository moduloPapelRepository) {
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
		this.papelRepository = papelRepository;
		this.moduloRepository = moduloRepository;
		this.moduloPapelRepository = moduloPapelRepository;
	}

	@Test
	void testaCadastroNutricionista() {

		Nutritionist p = new Nutritionist();
		p.setCpf("18956896533");
		p.setBirthDate(LocalDate.now());
		p.setEmail("ana@outlook.com.br");
		p.setAddress("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setName("Ana Eliza");
		p.setGender(Gender.F);
		p.setPassword("12345");
		p.setUserType(UserType.NUTRITIONIST);
		p.setCrn("123456789");
		p.setAnnuity(true);
		p.setLanguage("pt-Br");

		User nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCadastroPessoa() {

		Person p = new Person();
		p.setCpf("10490376690");
		p.setBirthDate(LocalDate.now());
		p.setEmail("rola@outlook.com.br");
		p.setAddress("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setName("Rafael");
		p.setGender(Gender.M);
		Person pessoaCadastrada = pessoaRepository.save(p);

		assertEquals(p.getEmail(), pessoaCadastrada.getEmail());
	}

	@Test
	void testaCadastroRecepcao() {

		User p = new User();
		p.setCpf("18956896533");
		p.setBirthDate(LocalDate.now());
		p.setEmail("picadura1@outlook.com.br");
		p.setAddress("Rua da preguiça, Barbacena-MG");
		p.setName("Xuxu baitola");
		p.setGender(Gender.F);
		p.setPassword("123456");
		p.setUserType(UserType.RECEPTIONIST);
		p.setLanguage("pt-Br");

		User nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCriarPapel() {

		Paper p = new Paper();
		p.setDescription("Usuario");

		Paper nc = papelRepository.save(p);

		assertNotNull(nc.getIdPaper());

	}

	@Test
	void testaCriarModulo() {

		Module m = new Module();
		m.setName("Cadastro");
		m.setOrders(1);
		m.setPathBase("app/cadastro");
		m.setIcon(null);

		Module nc = moduloRepository.save(m);

		assertNotNull(nc.getIdModule());

	}

	@Test
	void testaAssociacaoPapelModulo() {

		ModuleRole mp = new ModuleRole();
		mp.setModule(moduloRepository.findById(1L).get());
		mp.setPaper(papelRepository.findById(1L).get());
		
		ModuleRole nc = moduloPapelRepository.save(mp);

		assertNotNull(nc.getIdModuleRole());

	}
	
	@Test
	void testaAssociacaoUsuarioPapel() {

		User u = usuarioRepository.findByEmail(Criptografia.encode("ana@outlook.com.br")).get();
		 Set<Paper> papel = new HashSet<Paper>() ;
		 papel.add(papelRepository.findById(1L).get());
		u.setPapel(papel);
		
		User nc = usuarioRepository.save(u);

		assertNotNull(nc.getPapel());

	}
	
	

}
