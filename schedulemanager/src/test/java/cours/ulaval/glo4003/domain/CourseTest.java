package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {

	private Course course;
	private TimeDedicated timeDedicated;

	@Before
	public void setUp() {
		course = new Course();
		timeDedicated = mock(TimeDedicated.class);
	}

	@Test
	public void canInstantiateCourse() {
		String anAcronym = "acronym";
		String aTitle = "title";
		int credits = 2;
		String aDescription = "description";
		Cycle aCycle = Cycle.Premier;
		List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();
		course = new Course(anAcronym, aTitle, credits, aDescription, aCycle, prerequisites, timeDedicated);

		assertNotNull(course);
		assertEquals(anAcronym, course.getAcronym());
		assertEquals(aTitle, course.getTitle());
		assertEquals(credits, course.getCredits());
		assertEquals(aDescription, course.getDescription());
		assertEquals(aCycle, course.getCycle());
		assertEquals(prerequisites, course.getPrerequisites());
		assertEquals(timeDedicated, course.getTimeDedicated());
	}

	@Test
	public void canTellIfACourseIsConcomitting() {
		String anAcronym = "acronym";

		Prerequisite prerequisite = mock(Prerequisite.class);
		when(prerequisite.containsAcronym(anAcronym)).thenReturn(true);
		when(prerequisite.getIsConcomitant()).thenReturn(true);

		Course otherCourse = new Course();
		otherCourse.setAcronym(anAcronym);

		course.setPrerequisites(Arrays.asList(prerequisite));

		assertTrue(course.isConcomitting(otherCourse));
	}

	@Test
	public void canTellIfACourseIsNotConcomitting() {
		String anAcronym = "acronym";

		Prerequisite prerequisite = mock(Prerequisite.class);
		when(prerequisite.containsAcronym(anAcronym)).thenReturn(true);
		when(prerequisite.getIsConcomitant()).thenReturn(false);

		Course otherCourse = new Course();
		otherCourse.setAcronym(anAcronym);

		course.setPrerequisites(Arrays.asList(prerequisite));

		assertFalse(course.isConcomitting(otherCourse));
	}

	@Test
	public void canGetAcronym() {
		String acronym = "An acronym";
		course.setAcronym(acronym);

		assertEquals(acronym, course.getAcronym());
	}

	@Test
	public void canGetCredits() {
		int someCredits = 3;
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
		course.setCycle(Cycle.Deuxieme);

		assertEquals(Cycle.Deuxieme, course.getCycle());
	}

	@Test
	public void canGetDescription() {
		String aDescription = "This is a description";
		course.setDescription(aDescription);

		assertEquals(aDescription, course.getDescription());
	}

	@Test
	public void canGetTimeDedicated() {
		course.setTimeDedicated(timeDedicated);

		assertEquals(timeDedicated, course.getTimeDedicated());
	}

}
