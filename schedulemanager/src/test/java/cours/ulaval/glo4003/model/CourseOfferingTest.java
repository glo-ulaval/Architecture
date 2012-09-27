package cours.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;


public class CourseOfferingTest {
	
	private static final int NUMBER_OF_COURSES = 5;
	CourseOffering courseOffering;
	
	@Before
	public void setUp() {
		courseOffering = new CourseOffering();
	}
	
	@Test
	public void canInstantiateCourseOffering() {
		assertNotNull(courseOffering);
		assertTrue(courseOffering instanceof CourseOffering);
	}
	
	@Test
	public void canGetYear() {
		String goodYear = "2012-2013";
		courseOffering.setYear(goodYear);
		
		assertEquals(goodYear, courseOffering.getYear());
	}
	
	@Test
	public void canGetOffering() {
		Collection<String> offering = createValidOffering();
		courseOffering.setOffering(offering);
		
		assertEquals(offering, courseOffering.getOffering());
	}
	
	@Test
	public void canGetEmptyOfferingWhenDefault() {//TODO : À RENOMMER? pas d'idée...

		assertEquals(0, courseOffering.getOffering().size());
	}
	
	private ArrayList<String> createValidOffering() {
		String course = "ift-0000";
		ArrayList<String> offering = new ArrayList<String>();
		
		for(int i = 0; i < NUMBER_OF_COURSES; i++) {
			offering.add(course);
		}
		
		return offering;
	}

}
