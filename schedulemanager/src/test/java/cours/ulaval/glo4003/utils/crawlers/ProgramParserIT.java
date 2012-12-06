package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ProgramParserIT {

	private final static String PROGRAM_URL = "http://www2.ulaval.ca/les-etudes/programmes/repertoire/details/baccalaureat-en-informatique-b-sc-a.html";

	@Test
	public void canGetCoursesList() throws Exception {
		ProgramParser parser = new ProgramParser(PROGRAM_URL);

		List<String> coursesList = parser.getCoursesList();

		assertTrue(coursesList.size() > 40);
	}

}
