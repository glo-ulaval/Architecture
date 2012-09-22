package cours.ulaval.glo4003.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Course;

public class CourseRepositoryTest {

	private static int NUMBER_OF_COURSE = 5;

	private CourseRepository courseRepository;
	private ICourseRetriever courseRetriever;
	private Collection<Course> courses;

	@Before
	public void setUp() {
		createCourses();
		courseRetriever = mock(ICourseRetriever.class);
		when(courseRetriever.getCourses()).thenReturn(courses);

		courseRepository = new CourseRepository();
		courseRepository.setCourseRetriever(courseRetriever);
	}

	@Test
	public void canGetAllCourses() {
		assertEquals(NUMBER_OF_COURSE, courseRepository.getAll().size());
	}

	private void createCourses() {
		courses = new ArrayList<Course>();
		for (int i = 0; i < NUMBER_OF_COURSE; i++) {
			courses.add(mock(Course.class));
		}
	}
}
