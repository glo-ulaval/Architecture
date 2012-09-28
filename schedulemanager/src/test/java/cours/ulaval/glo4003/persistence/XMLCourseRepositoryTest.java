package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepositoryTest {

	@Test
	public void canInstantiateCourseRepository() throws Exception {
		XMLCourseRepository repository = new XMLCourseRepository();

		assertNotNull(repository);
	}

	@Test
	public void canFindAllCourses() throws Exception {
		XMLSerializer<CoursesDTO> serializer = mock(XMLSerializer.class);
		CoursesDTO pool = mock(CoursesDTO.class);
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(serializer.deserialize(resourcesPaths.getCoursesFilePath())).thenReturn(pool);

		XMLCourseRepository repository = new XMLCourseRepository();
		repository.setSerializer(serializer);

		assertEquals(pool, repository.findAll());
	}
}