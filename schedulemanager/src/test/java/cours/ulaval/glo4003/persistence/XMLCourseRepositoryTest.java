package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepositoryTest {

	private static final String AN_ACRONYM = "GLO-4003";

	private XMLCourseRepository repository;

	@Before
	public void setUp()
			throws Exception {
		XMLSerializer<CoursesDTO> mockedSerializer = mock(XMLSerializer.class);
		CoursesDTO dto = prepareMockedCourseDTO();
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		when(mockedSerializer.deserialize(resourcesPaths.getCoursesFilePath())).thenReturn(dto);

		repository = new XMLCourseRepository(mockedSerializer);
	}

	@Test
	public void canInstantiateCourseRepository()
			throws Exception {
		XMLCourseRepository repository = new XMLCourseRepository();

		assertNotNull(repository);
	}

	@Test
	public void canFindAllCourses()
			throws Exception {
		assertEquals(1, repository.findAll().size());
	}

	@Test
	public void canFindACourseByAcronym()
			throws Exception {
		Course course = repository.findByAcronym(AN_ACRONYM);

		assertEquals(AN_ACRONYM, course.getAcronym());
	}

	@Test
	public void canFindCoursesByOffering()
			throws Exception {
		Offering offering = mock(Offering.class);
		when(offering.getAcronyms()).thenReturn(Arrays.asList(AN_ACRONYM));

		List<Course> courses = repository.findByOffering(offering);

		assertEquals(1, courses.size());
		assertEquals(AN_ACRONYM, courses.get(0).getAcronym());
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