package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
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
		CoursesDTO dto = new CoursesDTO();
		List<Course> courses = new ArrayList<Course>();
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(serializer.deserialize(resourcesPaths.getCoursesFilePath())).thenReturn(dto);

		XMLCourseRepository repository = new XMLCourseRepository();
		repository.setSerializer(serializer);

		assertEquals(courses, repository.findAll());
	}
}