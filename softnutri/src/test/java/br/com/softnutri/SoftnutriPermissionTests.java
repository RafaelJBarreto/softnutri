package br.com.softnutri;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.enuns.ModuleAll;
import br.com.softnutri.enuns.PaperAll;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.ModuleRoleRepository;
import br.com.softnutri.repository.PaperRepository;

@SpringBootTest
class SoftnutriPermissionTests {

	private final PaperRepository papelRepository;

	private final ModuleRepository moduloRepository;

	private final ModuleRoleRepository moduloPapelRepository;

	@Autowired
	public SoftnutriPermissionTests(PaperRepository papelRepository, ModuleRepository moduloRepository,
			ModuleRoleRepository moduloPapelRepository) {
		this.papelRepository = papelRepository;
		this.moduloRepository = moduloRepository;
		this.moduloPapelRepository = moduloPapelRepository;
	}

	@Test
	void testaCriarPapel() {
		
		EnumSet<PaperAll> listPapers = listPapers();
		
		for (PaperAll p : listPapers) {
			Paper paper = papelRepository.findByDescription(p.name());
			if(paper == null) {
				Paper nc = papelRepository.save(new Paper(null, p.name()));
				assertNotNull(nc.getIdPaper());
			}
		}

	}

	@Test
	void testaCriarModulo() {
		
		EnumSet<ModuleAll> listModules = listModules();
		
		for (ModuleAll mod : listModules) {
			Module module = moduloRepository.findByName(mod.getName());
			if(module == null) {
				Module m = moduloRepository.save(new Module(null, mod.getName(), mod.getPathBase(), mod.getIcon(), mod.getOrders()));
				assertNotNull(m.getIdModule());
			}
		}
	}

	@Test
	void testaAssociacaoPapelModulo() {

		/*
		 * ModuleRole mp = new ModuleRole();
		 * mp.setModule(moduloRepository.findById(1L).get());
		 * mp.setPaper(papelRepository.findById(1L).get());
		 * 
		 * ModuleRole nc = moduloPapelRepository.save(mp);
		 * 
		 * assertNotNull(nc.getIdModuleRole());
		 */

	}

	public EnumSet<PaperAll> listPapers() {
		return EnumSet.allOf(PaperAll.class);
	}
	
	public EnumSet<ModuleAll> listModules() {
		return EnumSet.allOf(ModuleAll.class);
	}
}
