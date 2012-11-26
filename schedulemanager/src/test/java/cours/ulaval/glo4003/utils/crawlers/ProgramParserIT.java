package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ProgramParserIT {

	private final static String PROGRAM_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/y_bwckprog.p_afficher_fiche?p_session=200909&p_code_prog=B-GLO&p_code_majr=GLO&p_code_camp=";

	@Test
	public void canGetCoursesList() throws Exception {
		ProgramParser parser = new ProgramParser(PROGRAM_URL);

		List<String> coursesList = parser.getCoursesList();

		assertTrue(coursesList.size() > 0);
		for (String course : coursesList) {
			assertTrue(course.contains(ProgramParser.BASE_URL));
		}
	}

}
