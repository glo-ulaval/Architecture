package cours.ulaval.glo4003.repository.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ConfigManager;
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
		ConfigManager resourcesPaths = ConfigManager
				.getConfigManager();
		when(serializer.deserialize(resourcesPaths.getCoursesFilePath()))
				.thenReturn(pool);

		XMLCourseDAO dao = new XMLCourseDAO();
		dao.setSerializer(serializer);

		assertEquals(pool, dao.getCourses());
	}
}