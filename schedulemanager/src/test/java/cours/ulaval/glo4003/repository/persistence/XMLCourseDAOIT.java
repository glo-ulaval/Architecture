package cours.ulaval.glo4003.repository.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ResourcesLoader;
import cours.ulaval.glo4003.utils.XMLSerializer;

public class XMLCourseDAOIT {

	@Test
	public void canGetCourses() throws Exception {
		XMLSerializer<CoursesPool> serializer = new XMLSerializer<CoursesPool>(CoursesPool.class);
		serializer.setResourcesLoader(new ResourcesLoader());
		XMLCourseDAO dao = new XMLCourseDAO();
		dao.setSerializer(serializer);

		CoursesPool pool = dao.getCourses();

		assertEquals(1, pool.getCoursesCount());
	}
}
