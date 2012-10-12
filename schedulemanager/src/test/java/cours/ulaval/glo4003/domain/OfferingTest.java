package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.exception.InvalidOfferingOperation;

public class OfferingTest {

	private static final String VALID_ACRONYM = "a valid acronym";
	private static final int NUMBER_OF_COURSES = 5;
	private static final String GOOD_YEAR = "a good year";
	private static final Semester A_SEMESTER = Semester.Automne;
	Offering courseOffering;

	@Before
	public void setUp() {
		courseOffering = new Offering();
	}

	@Test
	public void canInstantiateCourseOffering() {
		assertNotNull(courseOffering);
		assertTrue(courseOffering instanceof Offering);
	}

	@Test
	public void canGetYear() {
		courseOffering.setYear(GOOD_YEAR);

		assertEquals(GOOD_YEAR, courseOffering.getYear());
	}

	@Test
	public void canAddCourse()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM, A_SEMESTER);

		assertTrue(courseOffering.getBySemester(A_SEMESTER).contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotAddCourseAlreadyInOffering()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM, A_SEMESTER);
		courseOffering.addCourse(VALID_ACRONYM, A_SEMESTER);
	}

	@Test
	public void canRemoveCourse()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM, A_SEMESTER);

		courseOffering.removeCourse(VALID_ACRONYM, A_SEMESTER);

		assertFalse(courseOffering.getBySemester(A_SEMESTER).contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotRemoveCourseNotInOffering()
			throws Exception {
		courseOffering.removeCourse(VALID_ACRONYM, A_SEMESTER);
	}

}
