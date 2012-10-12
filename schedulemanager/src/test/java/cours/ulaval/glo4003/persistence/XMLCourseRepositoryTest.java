package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepositoryTest {

	private static final String AN_ACRONYM = "GLO-4003";
	private static final Semester A_SEMESTER = Semester.Automne;

	private XMLCourseRepository repository;
	private XMLSerializer<CoursesXMLWrapper> mockedSerializer;

	@Before
	public void setUp()
			throws Exception {
		mockedSerializer = mock(XMLSerializer.class);
		CoursesXMLWrapper dto = prepareMockedCourseDTO();
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
		when(offering.getBySemester(A_SEMESTER)).thenReturn(Arrays.asList(AN_ACRONYM));

		List<Course> courses = repository.findByOffering(offering, A_SEMESTER);

		assertEquals(1, courses.size());
		assertEquals(AN_ACRONYM, courses.get(0).getAcronym());
	}

	@Test
	public void canStoreCourseMultipleTimes()
			throws Exception {
		Course course = mock(Course.class);

		repository.store(course);
		repository.store(course);

		verify(mockedSerializer, times(2)).serialize(any(CoursesXMLWrapper.class), anyString());
	}

	private CoursesXMLWrapper prepareMockedCourseDTO() {
		CoursesXMLWrapper dto = new CoursesXMLWrapper();
		List<Course> courses = new ArrayList<Course>();
		Course course = mock(Course.class);
		when(course.getAcronym()).thenReturn(AN_ACRONYM);
		courses.add(course);
		dto.setCourses(courses);
		return dto;
	}
}