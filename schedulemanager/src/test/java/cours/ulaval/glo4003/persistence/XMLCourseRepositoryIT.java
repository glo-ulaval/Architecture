package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLCourseRepositoryIT extends ITTestBase {

	private XMLSerializer<CoursesXMLWrapper> serializer;
	private XMLCourseRepository repository;

	@Before
	public void setup()
			throws Exception {
		serializer = new XMLSerializer<CoursesXMLWrapper>(CoursesXMLWrapper.class);
		serializer.setResourcesLoader(new ResourcesLoader());

		repository = new XMLCourseRepository(serializer);
	}

	@After
	public void tearDown() {
		repository.clear();
	}

	@Test
	public void canGetCourses()
			throws Exception {
		repository.store(CreateACourse());

		Course course = repository.findByAcronym("GLO-4444");

		assertEquals(CreateACourse(), course);
	}

	@Test
	public void canFindAllCourses()
			throws Exception {
		Course course = CreateACourse();
		course.setAcronym("IFT-1200");

		repository.store(CreateACourse());
		repository.store(course);

		List<Course> courses = repository.findAll();

		assertEquals(2, courses.size());
	}

	@Test
	public void canClearAllTheCourses()
			throws Exception {
		repository.store(CreateACourse());

		repository.clear();

		assertEquals(0, repository.findAll().size());
	}

	private static Course CreateACourse() {
		Prerequisite prerequisite = new Prerequisite();
		prerequisite.setAcronyms(Arrays.asList("GLO-2004", "GLO-1001"));
		prerequisite.setIsConcomitant(true);

		TimeDedicated timeDedicated = new TimeDedicated(3, 2, 4);

		Course course = new Course("GLO-4444", "Architecture Logicielle", 3, "Derp derp herp derp McDerpington", Cycle.Premier,
				Arrays.asList(prerequisite), timeDedicated);

		return course;
	}
}