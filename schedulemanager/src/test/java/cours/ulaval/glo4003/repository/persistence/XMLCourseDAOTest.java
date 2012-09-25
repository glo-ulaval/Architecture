package cours.ulaval.glo4003.repository.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ResourcesPaths;
import cours.ulaval.glo4003.utils.XMLSerializer;

public class XMLCourseDAOTest {

	@Test
	public void canInstantiateCourseDAO() throws Exception {
		XMLCourseDAO dao = new XMLCourseDAO();

		assertNotNull(dao);
	}

	@Test
	public void canGetCourses() throws Exception {
		XMLSerializer<CoursesPool> serializer = mock(XMLSerializer.class);
		CoursesPool pool = mock(CoursesPool.class);
		when(serializer.deserialize(ResourcesPaths.COURSES_FILE)).thenReturn(pool);

		XMLCourseDAO dao = new XMLCourseDAO();
		dao.setSerializer(serializer);

		assertEquals(pool, dao.getCourses());
	}
}