package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.Cycle;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLCourseRepositoryIT {

	@Test
	public void canGetCourses() throws Exception {
		XMLSerializer<CoursesDTO> serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
		serializer.setResourcesLoader(new ResourcesLoader());
		XMLCourseRepository repository = new XMLCourseRepository();
		repository.setSerializer(serializer);

		List<Course> courses = repository.findAll();
		Course course = courses.get(0);

		assertEquals(8, courses.size());
		assertEquals("GLO-1010", course.getAcronym());
		assertEquals("Pratique du génie logiciel", course.getTitle());
		assertEquals(1, course.getCredits());
		assertNotNull(course.getDescription());
		assertEquals(Cycle.FIRST, course.getCycle());
	}
}