package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import br.com.softnutri.domain.Calendar;
import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.Gender;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.repository.CalendarRepository;
import br.com.softnutri.repository.PersonRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

@SpringBootTest
class SoftnutriApplicationTests {

	private final PersonRepository pessoaRepository;
	private final UserRepository usuarioRepository;
	private final CalendarRepository calendarRepository;

	@Autowired
	public SoftnutriApplicationTests(PersonRepository pessoaRepository, UserRepository usuarioRepository, CalendarRepository calendarRepository) {
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
		this.calendarRepository = calendarRepository;
	}

	@Test
	void testaCadastroNutricionista() {

		User p = new User();
		p.setCpf(Criptografia.encode("18956896533"));
		p.setBirthDate(LocalDate.now());
		p.setEmail(Criptografia.encode("ana@outlook.com.br"));
		p.setAddress(Criptografia.encode("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG"));
		p.setName(Criptografia.encode("Ana Eliza"));
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
		p.setCpf(Criptografia.encode("10490376690"));
		p.setBirthDate(LocalDate.now());
		p.setEmail(Criptografia.encode("teste@outlook.com.br"));
		p.setAddress(Criptografia.encode("Rua professor Agenor Soares, 125, Santa cecília, Barbacena-MG"));
		p.setName(Criptografia.encode("Rafael"));
		p.setGender(Gender.M);
		Person pessoaCadastrada = pessoaRepository.save(p);

		assertEquals(p.getEmail(), pessoaCadastrada.getEmail());
	}

	@Test
	void testaCadastroRecepcao() {

		Optional<User> userAux = usuarioRepository.findByEmail(Criptografia.encode("teste2@outlook.com.br"));
		User p = new User();
		p.setCpf(Criptografia.encode("18956896533"));
		p.setBirthDate(LocalDate.now());
		p.setEmail(Criptografia.encode("teste2@outlook.com.br"));
		p.setAddress(Criptografia.encode("Rua da preguiça, Barbacena-MG"));
		p.setName(Criptografia.encode("Teste"));
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
	
	@Test
	@AfterTestExecution
	void testaCadastroAgenda() {

		List<User> professional = usuarioRepository.findByUserType(UserType.NUTRITIONIST);
		List<User> receptionist = usuarioRepository.findByUserType(UserType.RECEPTIONIST);
		List<User> patient = usuarioRepository.findByUserType(UserType.PATIENT);
		
		Calendar calendar = new Calendar();
		
		calendar.setCancel(true);
		calendar.setCompleted(false);
		calendar.setDateOfDay(LocalDate.now() );
		calendar.setHourOfDay(LocalTime.now());
		calendar.setNote("teste 2");
		if(!professional.isEmpty()) {
			calendar.setProfessional(professional.get(0));
		}
		if(!receptionist.isEmpty()) {
			calendar.setReceptionist(receptionist.get(0));
		}
		
		if(!patient.isEmpty()) {
			calendar.setPatient(patient.get(0));
		}
		
		
		Calendar nc = calendarRepository.save(calendar);

		assertEquals(calendar.getDateOfDay(), nc.getDateOfDay());

	}
}
