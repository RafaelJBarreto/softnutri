package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.PersonPaper;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.ModuleAll;
import br.com.softnutri.enuns.PaperAll;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.PaperRepository;
import br.com.softnutri.repository.PersonPaperRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;
import br.com.softnutri.util.Util;

@SpringBootTest
class SoftnutriPermissionTests {

	private final PaperRepository papelRepository;
	private final ModuleRepository moduloRepository;
	private final ModuleRoleRepository moduloPapelRepository;
	private final UserRepository usuarioRepository;
	private final PersonPaperRepository personPaperRepository;

	@Autowired
	public SoftnutriPermissionTests(PaperRepository papelRepository, ModuleRepository moduloRepository,
			ModuleRoleRepository moduloPapelRepository, UserRepository usuarioRepository, PersonPaperRepository personPaperRepository) {
		this.papelRepository = papelRepository;
		this.moduloRepository = moduloRepository;
		this.moduloPapelRepository = moduloPapelRepository;
		this.usuarioRepository = usuarioRepository;
		this.personPaperRepository = personPaperRepository;
	}

	@Test
	void testaCriarPapel() {
		EnumSet<PaperAll> listPapers = listPapers();
		List<Paper> papers = papelRepository.findAll();
		for (PaperAll p : listPapers) {
			Paper paper = papers.stream().filter(x -> p.name().equals(x.getDescription())).findAny().orElse(null);
			if(paper == null) {
				Paper nc = papelRepository.save(new Paper(null, p.name(), p.getGet(), p.getPost(), p.getPut(), p.getDelete()));
				assertNotNull(nc.getIdPaper());
			}
		}

	}

	@Test
	void testaCriarModulo() {
		EnumSet<ModuleAll> listModules = listModules();
		List<Module> modules = moduloRepository.findAll();
		for (ModuleAll mod : listModules) {
			Module module = modules.stream().filter(x -> mod.getName().toUpperCase().contains(x.getName().toUpperCase())).findAny().orElse(null);
			if(module == null) {
				Module m = moduloRepository.save(new Module(null, mod.getName(), mod.getPathBase(), mod.getIcon(), mod.getOrders()));
				assertNotNull(m.getIdModule());
			}
		}
	}

	@Test
	@AfterTestExecution
	void testaAssociacaoPapelModulo() {
		List<Module> modules = moduloRepository.findAll();
		List<Paper> papers = papelRepository.findAll();
		List<ModuleRole> moduleRoles = moduloPapelRepository.findAll();
		
		EnumSet<ModuleAll> listModules = listModules();
		for (ModuleAll mod : listModules) {
			
			Module module = modules.stream().filter(x -> mod.getName().equals(x.getName())).findAny().orElse(null);
			if(module != null) {
				ModuleRole moduleRole = moduleRoles.stream().filter(x -> module.getName().equals(x.getModule().getName())).findAny().orElse(null);
				if(moduleRole == null) {					
					for (PaperAll paperAll : mod.getListPapers()) {
						Paper paper = papers.stream().filter(x -> paperAll.name().equals(x.getDescription())).findAny().orElse(null);
						if(paper != null) {
							ModuleRole nc = moduloPapelRepository.save(new ModuleRole(paper, module));
							assertNotNull(nc.getIdModuleRole());
						}
					}
					
				}
			}
			
		}
		
	}
	
	@Test
	@AfterTestExecution
	void testaAssociacaoPapelUsuario() {
		Optional<User> user = usuarioRepository.findByEmail(Criptografia.encode("ana@outlook.com.br"));
		
		if(user.isPresent()) {
			
			User userO = user.get();
			List<Paper> papers = papelRepository.findAll();
			List<PersonPaper> permissions = this.personPaperRepository.findByUserIdPerson(userO.getIdPerson()).stream().toList();
			for (Paper paper : papers) {
				PersonPaper personPaper = permissions.stream().filter(x -> paper.getIdPaper().equals(x.getPaper().getIdPaper())).findAny().orElse(null);
				if(personPaper == null)
					this.personPaperRepository.save(new PersonPaper(null, new User(userO.getIdPerson()), new Paper(paper.getIdPaper()), Util.getGet(paper.getGet()), Util.getPost(paper.getPost()), Util.getPut(paper.getPut()), Util.getDelete(paper.getDelete())));
			}
			
			assertNotNull(papers);
		}
		
		
	}
	

	public EnumSet<PaperAll> listPapers() {
		return EnumSet.allOf(PaperAll.class);
	}
	
	public EnumSet<ModuleAll> listModules() {
		return EnumSet.allOf(ModuleAll.class);
	}
}
