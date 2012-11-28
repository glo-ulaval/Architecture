package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.exception.InvalidOfferingOperation;

public class OfferingTest {

	private static final String VALID_ACRONYM = "a valid acronym";
	private static final String INVALID_ACRONYM = "acronym not in offering";
	private static final String GOOD_YEAR = "a good year";
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
		courseOffering.addCourse(VALID_ACRONYM);

		assertTrue(courseOffering.getOffering().contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotAddCourseAlreadyInOffering()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);
		courseOffering.addCourse(VALID_ACRONYM);
	}

	@Test
	public void canRemoveCourse()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);

		courseOffering.removeCourse(VALID_ACRONYM);

		assertFalse(courseOffering.getOffering().contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotRemoveCourseNotInOffering()
			throws Exception {
		courseOffering.removeCourse(VALID_ACRONYM);
	}

	@Test
	public void canTellIfCourseIsInOffering()
			throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);

		assertTrue(courseOffering.hasCourse(VALID_ACRONYM));
		assertFalse(courseOffering.hasCourse(INVALID_ACRONYM));
	}

}
