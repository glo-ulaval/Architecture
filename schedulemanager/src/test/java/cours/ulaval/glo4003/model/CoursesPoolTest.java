package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class CoursesPoolTest {

	@Test
	public void canGetCoursesCountWithNoCourse() {
		CoursesPool pool = new CoursesPool();

		assertEquals(0, pool.getCoursesCount());
	}

	@Test(expected = NoSuchElementException.class)
	public void canIterateOnCoursesPoolWhenNoCourse() {
		CoursesPool pool = new CoursesPool();

		pool.iterator().next();
	}

	@Test
	public void canGetCoursesCountWithCourses() {
		CoursesPool pool = new CoursesPool();
		pool.setCourses(getCourses());

		assertEquals(1, pool.getCoursesCount());
	}

	@Test
	public void canIterateOnCoursesPoolWithCourses() {
		CoursesPool pool = new CoursesPool();
		pool.setCourses(getCourses());

		assertNotNull(pool.iterator().next());
	}

	private List<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course = mock(Course.class);
		courses.add(course);

		return courses;
	}
}
