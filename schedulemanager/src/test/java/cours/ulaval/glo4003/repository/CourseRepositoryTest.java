package cours.ulaval.glo4003.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;

public class CourseRepositoryTest {

	private CourseRepository courseRepository;
	private ICourseRetriever courseRetriever;
	private CoursesPool courses;

	@Before
	public void setUp() throws Exception {
		courseRetriever = mock(ICourseRetriever.class);
		when(courseRetriever.getCourses()).thenReturn(courses);

		courseRepository = new CourseRepository();
		courseRepository.setCourseRetriever(courseRetriever);
	}

	@Test
	public void canGetAllCourses() throws Exception {
		assertEquals(courses, courseRepository.getAll());
	}
}
