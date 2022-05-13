package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.ModuleRole;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.User;
import br.com.softnutri.enuns.ModuleAll;
import br.com.softnutri.enuns.PaperAll;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.PaperRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

@SpringBootTest
class SoftnutriPermissionTests {

	private final PaperRepository papelRepository;
	private final ModuleRepository moduloRepository;
	private final ModuleRoleRepository moduloPapelRepository;
	private final UserRepository usuarioRepository;

	@Autowired
	public SoftnutriPermissionTests(PaperRepository papelRepository, ModuleRepository moduloRepository,
			ModuleRoleRepository moduloPapelRepository, UserRepository usuarioRepository) {
		this.papelRepository = papelRepository;
		this.moduloRepository = moduloRepository;
		this.moduloPapelRepository = moduloPapelRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Test
	void testaCriarPapel() {
		EnumSet<PaperAll> listPapers = listPapers();
		List<Paper> papers = papelRepository.findAll();
		for (PaperAll p : listPapers) {
			Paper paper = papers.stream().filter(x -> p.name().equals(x.getDescription())).findAny().orElse(null);
			if(paper == null) {
				Paper nc = papelRepository.save(new Paper(null, p.name()));
				assertNotNull(nc.getIdPaper());
			}
		}

	}

	@Test
	void testaCriarModulo() {
		EnumSet<ModuleAll> listModules = listModules();
		List<Module> modules = moduloRepository.findAll();
		for (ModuleAll mod : listModules) {
			Module module = modules.stream().filter(x -> mod.name().equals(x.getName())).findAny().orElse(null);
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
			Set<Paper> papel = new HashSet<Paper>() ;
			for (Paper paper : papers) {
				Paper p = userO.getPapel().stream().filter(x -> paper.getDescription().equals(x.getDescription())).findAny().orElse(null);
				if(p == null) {
					papel.add(paper);
				}

			}
			if(!papel.isEmpty()) {
				userO.setPapel(papel);
				User nc = usuarioRepository.save(user.get());
				assertNotNull(nc.getPapel());
			}
		}
		
		
	}
	

	public EnumSet<PaperAll> listPapers() {
		return EnumSet.allOf(PaperAll.class);
	}
	
	public EnumSet<ModuleAll> listModules() {
		return EnumSet.allOf(ModuleAll.class);
	}
}
