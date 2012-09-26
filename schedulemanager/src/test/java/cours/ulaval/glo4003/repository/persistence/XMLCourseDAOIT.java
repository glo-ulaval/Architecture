package cours.ulaval.glo4003.repository.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.model.Cycle;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLCourseDAOIT {

	@Test
	public void canGetCourses() throws Exception {
		XMLSerializer<CoursesPool> serializer = new XMLSerializer<CoursesPool>(CoursesPool.class);
		serializer.setResourcesLoader(new ResourcesLoader());
		XMLCourseDAO dao = new XMLCourseDAO();
		dao.setSerializer(serializer);

		CoursesPool pool = dao.getCourses();
		Course course = pool.iterator().next();

		assertEquals(8, pool.getCoursesCount());
		assertEquals("GLO-1010", course.getAcronym());
		assertEquals("Pratique du génie logiciel", course.getTitle());
		assertEquals(1, course.getCredits());
		assertNotNull(course.getDescription());
		assertEquals(Cycle.FIRST, course.getCycle());
	}
}