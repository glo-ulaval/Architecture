package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {

	private Course course;

	@Before
	public void setUp() {
		course = new Course();
	}

	@Test
	public void canInstantiateCourse() {
		assertNotNull(course);
	}

	@Test
	public void canGetAcronym() {
		String acronym = "An acronym";
		course.setAcronym(acronym);

		assertEquals(acronym, course.getAcronym());
	}

	@Test
	public void canGetCredits() {
		Integer someCredits = 3;
		course.setCredits(someCredits);

		assertEquals(someCredits, course.getCredits());
	}

	@Test
	public void canGetTitle() {
		String aTitle = "A title";
		course.setTitle(aTitle);

		assertEquals(aTitle, course.getTitle());
	}

	@Test
	public void canGetPrerequisitesWithNoPrerequisites() {
		List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();
		course.setPrerequisites(prerequisites);

		assertEquals(prerequisites, course.getPrerequisites());
	}

	@Test
	public void canGetPrerequisitesWithPrerequisites() {
		List<Prerequisite> prerequisites = Arrays.asList(mock(Prerequisite.class));
		course.setPrerequisites(prerequisites);

		assertEquals(1, course.getPrerequisites().size());
	}

	@Test
	public void canGetCycle() {
		course.setCycle(Cycle.SECOND);

		assertEquals(Cycle.SECOND, course.getCycle());
	}

	@Test
	public void canGetDescription() {
		String aDescription = "This is a description";
		course.setDescription(aDescription);

		assertEquals(aDescription, course.getDescription());
	}
}
