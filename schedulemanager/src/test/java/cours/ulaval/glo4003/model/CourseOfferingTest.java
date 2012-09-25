package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseOfferingTest {
	
	@Test
	public void canInstantiateCourseOffering() {
		CourseOffering courseOffering = new CourseOffering();
		
		assertNotNull(courseOffering);
		assertTrue(courseOffering instanceof CourseOffering);
	}
}
