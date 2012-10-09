package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.Cycle;
import cours.ulaval.glo4003.model.Prerequisite;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLCourseRepositoryIT {

	@Test
	public void canGetCourses()
			throws Exception {
		XMLSerializer<CoursesDTO> serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
		serializer.setResourcesLoader(new ResourcesLoader());
		XMLCourseRepository repository = new XMLCourseRepository(serializer);

		Prerequisite prerequisite = new Prerequisite();
		prerequisite.setAcronyms(Arrays.asList("GLO-2004", "GLO-1001"));
		prerequisite.setIsConcomitant(true);
		Course course = new Course("GLO-4444", "Architecture Logicielle", 3, "Derp derp herp derp McDerpington", Cycle.Premier,
				Arrays.asList(prerequisite));

		repository.store(course);

		List<Course> courses = repository.findAll();
		course = repository.findByAcronym("GLO-4444");

		assertTrue(courses.contains(course));
		assertEquals("GLO-4444", course.getAcronym());
		assertEquals("Architecture Logicielle", course.getTitle());
		assertEquals(3, course.getCredits());
		assertEquals("Derp derp herp derp McDerpington", course.getDescription());
		assertEquals(Cycle.Premier, course.getCycle());

		List<Prerequisite> prerequisites = course.getPrerequisites();
		assertEquals(1, prerequisites.size());

		Prerequisite aPrerequisite = prerequisites.get(0);
		List<String> acronyms = aPrerequisite.getAcronyms();
		assertEquals(2, acronyms.size());
		assertEquals("GLO-2004", acronyms.get(0));
	}
}