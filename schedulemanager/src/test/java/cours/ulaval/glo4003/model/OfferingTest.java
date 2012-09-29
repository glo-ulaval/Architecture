package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


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
	public void canAddCourse() {
		courseOffering.addCourse(VALID_ACRONYM);
		
		assertTrue(courseOffering.getOffering().contains(VALID_ACRONYM));
	}
	
	@Test
	public void canRemoveCourse() {
		courseOffering.addCourse(VALID_ACRONYM);
		
		courseOffering.removeCourse(VALID_ACRONYM);
		
		assertFalse(courseOffering.getOffering().contains(VALID_ACRONYM));
	}
	
	@Test
	public void canGetOffering() {
		ArrayList<String> offering = createValidAcronymList();
		courseOffering.setOffering(offering);
		
		assertEquals(offering, courseOffering.getOffering());
	}
	
	@Test
	public void canSetOfferingByContructor() {
		Offering newCourseOffering = new Offering(GOOD_YEAR, courseOffering);
		
		assertEquals(GOOD_YEAR, newCourseOffering.getYear());
		assertEquals(courseOffering.getOffering(), newCourseOffering.getOffering());
		
	}
	
	@Test
	public void canGetEmptyOfferingWhenDefault() {//TODO : À RENOMMER? pas d'idée...

		assertEquals(0, courseOffering.getOffering().size());
	}
	
	private ArrayList<String> createValidAcronymList() {
		String course = "ift-0000";
		ArrayList<String> acronyms = new ArrayList<String>();
		
		for(int i = 0; i < NUMBER_OF_COURSES; i++) {
			acronyms.add(course);
		}
		
		return acronyms;
	}

}