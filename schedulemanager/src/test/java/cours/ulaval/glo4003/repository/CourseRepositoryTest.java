package cours.ulaval.glo4003.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.repository.persistence.XMLCourseDAO;

public class CourseRepositoryTest {

	private CourseRepository courseRepository;
	private XMLCourseDAO courseDAO;
	private CoursesPool courses;

	@Before
	public void setUp() throws Exception {
		courseDAO = mock(XMLCourseDAO.class);

		courseRepository = new CourseRepository();
		courseRepository.setCourseDAO(courseDAO);
	}

	@Test
	public void canGetAllCoursesWithNoCourse() throws Exception {
		when(courseDAO.getCourses()).thenReturn(courses);

		assertEquals(courses, courseRepository.getAll());
	}

	@Test
	public void canGetAllCoursesWithCourses() throws Exception {
		courses = new CoursesPool();
		Course course = mock(Course.class);
		courses.setCourses(Arrays.asList(course));
		when(courseDAO.getCourses()).thenReturn(courses);

		assertEquals(courses, courseRepository.getAll());
	}
}
