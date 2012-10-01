package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.Cycle;
import cours.ulaval.glo4003.model.Prerequisite;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLCourseRepositoryIT {

	@Test
	public void canGetCourses() throws Exception {
		XMLSerializer<CoursesDTO> serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
		serializer.setResourcesLoader(new ResourcesLoader());
		XMLCourseRepository repository = new XMLCourseRepository(serializer);

		List<Course> courses = repository.findAll();
		Course course = repository.findByAcronym("GLO-2003");

		assertEquals(8, courses.size());
		assertEquals("GLO-2003", course.getAcronym());
		assertEquals("Introduction aux processus du génie logiciel", course.getTitle());
		assertEquals(3, course.getCredits());
		assertNotNull(course.getDescription());
		assertEquals(Cycle.Premier, course.getCycle());

		List<Prerequisite> prerequisites = course.getPrerequisites();
		assertEquals(1, prerequisites.size());

		Prerequisite prerequisite = prerequisites.get(0);
		List<String> acronyms = prerequisite.getAcronyms();
		assertEquals(3, acronyms.size());
		assertEquals("GLO-2004", acronyms.get(0));
	}
}