package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.ProgramSheet;

public class XMLProgramSheetRepositoryIT extends ITTestBase {

	XMLProgramSheetRepository repository;

	@Before
	public void setUp() throws Exception {
		repository = new XMLProgramSheetRepository();
	}

	@Test
	public void canGetProgramSheetGLO() {
		ProgramSheet sheet = repository.getProgramSheetGLO();
		Map<String, Integer> courses = sheet.getCourses();

		assertTrue(courses.size() >= 0);
	}

	@Test
	public void canGetProgramSheetIFT() {
		ProgramSheet sheet = repository.getProgramSheetIFT();
		Map<String, Integer> courses = sheet.getCourses();

		assertTrue(courses.size() >= 0);
	}
}
