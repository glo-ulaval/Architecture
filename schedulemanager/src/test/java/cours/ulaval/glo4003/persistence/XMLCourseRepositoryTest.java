package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.SerializationException;
import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepositoryTest {

	private static final String AN_ACRONYM = "GLO-4003";

	@Test
	public void canInstantiateCourseRepository() throws Exception {
		XMLCourseRepository repository = new XMLCourseRepository();

		assertNotNull(repository);
	}

	@Test
	public void canFindAllCourses() throws Exception {
		XMLSerializer<CoursesDTO> mockedSerializer = mock(XMLSerializer.class);
		CoursesDTO dto = new CoursesDTO();
		List<Course> courses = new ArrayList<Course>();
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(mockedSerializer.deserialize(resourcesPaths.getCoursesFilePath())).thenReturn(dto);

		XMLCourseRepository repository = new XMLCourseRepository(mockedSerializer);

		assertEquals(courses, repository.findAll());
	}
	
	@Test
	public void canFindACourseByAcronym() throws Exception{
		XMLSerializer<CoursesDTO> mockedSerializer = mock(XMLSerializer.class);
		CoursesDTO dto = prepareMockedCourseDTO();
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(mockedSerializer.deserialize(resourcesPaths.getCoursesFilePath())).thenReturn(dto);
		XMLCourseRepository repository = new XMLCourseRepository(mockedSerializer);
		
		Course course = repository.findByAcronym(AN_ACRONYM);

		assertEquals(AN_ACRONYM, course.getAcronym());
	}

	private CoursesDTO prepareMockedCourseDTO() {
		CoursesDTO dto = new CoursesDTO();
		List<Course> courses = new ArrayList<Course>();
		Course course = mock(Course.class);
		when(course.getAcronym()).thenReturn(AN_ACRONYM);
		courses.add(course);
		dto.setCourses(courses);
		return dto;
	}
}