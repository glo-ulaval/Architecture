package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ProgramSheetTest {

	private static final String A_FIRST_ACRONYM = "GLO-2000";
	private static final String A_SECOND_ACRONYM = "GLO-2001";
	private static final String A_THIRD_ACRONYM = "GLO-2002";

	private ProgramSheet programSheet;

	@Before
	public void setUp() {
		programSheet = new ProgramSheet(createCoursesMap());
	}

	@Test
	public void canGetIfTwoCoursesAreOnSameLevelWhenTheyAre() {
		assertTrue(programSheet.areCoursesSameLevel(A_FIRST_ACRONYM, A_THIRD_ACRONYM));
	}

	@Test
	public void canGetIfTwoCoursesAreOnSameLevelWhenTheyArent() {
		assertFalse(programSheet.areCoursesSameLevel(A_FIRST_ACRONYM, A_SECOND_ACRONYM));
	}

	private Map<String, Integer> createCoursesMap() {
		Map<String, Integer> courses = new HashMap<String, Integer>();
		courses.put(A_FIRST_ACRONYM, 1);
		courses.put(A_SECOND_ACRONYM, 2);
		courses.put(A_THIRD_ACRONYM, 1);

		return courses;
	}
}
