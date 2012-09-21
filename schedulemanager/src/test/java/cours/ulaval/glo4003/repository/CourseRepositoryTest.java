package cours.ulaval.glo4003.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Course;

public class CourseRepositoryTest {

	private static int NUMBER_OF_COURSE = 5;

	private CourseRepository courseRepository;
	private ICourseRetriever courseRetriever;
	private Collection<Course> courses;

	@Before
	public void setUp() throws Exception {
		courses = new ArrayList<Course>();
		for (int i = 0; i < NUMBER_OF_COURSE; i++) {
			courses.add(mock(Course.class));
		}
		courseRetriever = mock(ICourseRetriever.class);
		when(courseRetriever.getCourses()).thenReturn(courses);

		courseRepository = new CourseRepository();
		courseRepository.setCourseRetriever(courseRetriever);
	}

	@Test
	public void canGetAllCourses() {
		Assert.assertTrue(NUMBER_OF_COURSE == courseRepository.getAll().size());
		Assert.assertTrue(courseRepository.getAll().size() == NUMBER_OF_COURSE);
	}
}
