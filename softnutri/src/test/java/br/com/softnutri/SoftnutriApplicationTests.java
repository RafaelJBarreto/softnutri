package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.Gender;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.repository.PersonRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

@SpringBootTest
class SoftnutriApplicationTests {

	private final PersonRepository pessoaRepository;

	private final UserRepository usuarioRepository;

	@Autowired
	public SoftnutriApplicationTests(PersonRepository pessoaRepository, UserRepository usuarioRepository) {
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Test
	void testaCadastroNutricionista() {

		User p = new User();
		p.setCpf("18956896533");
		p.setBirthDate(LocalDate.now());
		p.setEmail("ana@outlook.com.br");
		p.setAddress("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setName("Ana Eliza");
		p.setGender(Gender.F);
		p.setPassword("12345");
		p.setUserType(UserType.NUTRITIONIST);
		p.setCrn("123456789");
		p.setLanguage("pt-Br");

		User nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}

	@Test
	void testaCadastroPessoa() {

		Person p = new Person();
		p.setCpf("10490376690");
		p.setBirthDate(LocalDate.now());
		p.setEmail("teste@outlook.com.br");
		p.setAddress("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG");
		p.setName("Rafael");
		p.setGender(Gender.M);
		Person pessoaCadastrada = pessoaRepository.save(p);

		assertEquals(p.getEmail(), pessoaCadastrada.getEmail());
	}

	@Test
	void testaCadastroRecepcao() {

		Optional<User> userAux = usuarioRepository.findByEmail(Criptografia.encode("teste2@outlook.com.br"));
		User p = new User();
		p.setCpf("18956896533");
		p.setBirthDate(LocalDate.now());
		p.setEmail("teste2@outlook.com.br");
		p.setAddress("Rua da preguiça, Barbacena-MG");
		p.setName("Teste");
		p.setGender(Gender.M);
		p.setPassword("123456");
		p.setLanguage("pt-Br");
		if (userAux.isPresent()) {
			p.setUserType(UserType.NUTRITIONIST);
			p.setCrn("123456789");
			p.setIdPerson(userAux.get().getIdPerson());
			p.setDateRegister(userAux.get().getDateRegister());
		}
		User nc = usuarioRepository.save(p);

		assertEquals(p.getEmail(), nc.getEmail());

	}
}
