package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Course;

public class CoursesDTOTest {

	@Test
	public void canGetCoursesCountWithNoCourse() {
		CoursesDTO dto = new CoursesDTO();

		assertEquals(0, dto.getCourses().size());
	}

	@Test
	public void canGetCoursesCountWithCourses() {
		CoursesDTO dto = new CoursesDTO();
		dto.setCourses(getCourses());

		assertEquals(1, dto.getCourses().size());
	}

	private List<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course = mock(Course.class);
		courses.add(course);

		return courses;
	}
}
