package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.domain.Nutritionist;
import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.Gender;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.repository.PersonRepository;
import br.com.softnutri.repository.UserRepository;

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
}
