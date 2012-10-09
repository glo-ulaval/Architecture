package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.model.exception.InvalidOfferingOperation;

public class OfferingTest {

	private static final String VALID_ACRONYM = "a valid acronym";
	private static final int NUMBER_OF_COURSES = 5;
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
	public void canAddCourse() throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);

		assertTrue(courseOffering.getAcronyms().contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotAddCourseAlreadyInOffering() throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);
		courseOffering.addCourse(VALID_ACRONYM);
	}

	@Test
	public void canRemoveCourse() throws Exception {
		courseOffering.addCourse(VALID_ACRONYM);

		courseOffering.removeCourse(VALID_ACRONYM);

		assertFalse(courseOffering.getAcronyms().contains(VALID_ACRONYM));
	}

	@Test(expected = InvalidOfferingOperation.class)
	public void cannotRemoveCourseNotInOffering() throws Exception {
		courseOffering.removeCourse(VALID_ACRONYM);
	}

	@Test
	public void canGetOffering() {
		ArrayList<String> offering = createValidAcronymList();
		courseOffering.setOffering(offering);

		assertEquals(offering, courseOffering.getAcronyms());
	}

	@Test
	public void canSetOfferingByContructor() {
		Offering newCourseOffering = new Offering(GOOD_YEAR, courseOffering);

		assertEquals(GOOD_YEAR, newCourseOffering.getYear());
		assertEquals(courseOffering.getAcronyms(),
				newCourseOffering.getAcronyms());

	}

	@Test
	public void canGetEmptyOfferingWhenDefault() {

		assertEquals(0, courseOffering.getAcronyms().size());
	}

	private ArrayList<String> createValidAcronymList() {
		String course = "ift-0000";
		ArrayList<String> acronyms = new ArrayList<String>();

		for (int i = 0; i < NUMBER_OF_COURSES; i++) {
			acronyms.add(course);
		}

		return acronyms;
	}

}
