package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class SearchControllerTest {

	private static final String A_DESCRIPTION = "This is description";
	private static final String A_KEYWORD = "description";

	@Mock
	private CourseRepository repository;
	@InjectMocks
	private SearchController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(repository.findAll()).thenReturn(Arrays.asList(getCourse()));
	}

	@Test
	public void controllerReturnsCorrectModelAndView() throws Exception {
		ModelAndView mv = controller.search(A_KEYWORD);

		assertTrue(mv.getModel().containsKey("keywords"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void controllerReturnsCorrectModelAndViewWhenCourseDoesntContainKeyword() throws Exception {
		ModelAndView mv = controller.search("toto");

		List<Course> courses = (List<Course>) mv.getModel().get("courses");
		assertEquals(0, courses.size());
	}

	@Test
	public void controllerReturnsCorrectModelAndViewWhenDuplicateCoursesFound() throws Exception {
		when(repository.findAll()).thenReturn(Arrays.asList(getCourse(), getCourse()));

		ModelAndView mv = controller.search(A_KEYWORD);

		List<Course> courses = (List<Course>) mv.getModel().get("courses");
		assertEquals(1, courses.size());
	}

	@Test
	public void controllerCanCorrectlyHighlightWords() throws Exception {
		ModelAndView mv = controller.search(A_KEYWORD);

		Course course = ((List<Course>) mv.getModel().get("courses")).get(0);
		assertEquals(course.getDescription(), "This is <span class=\"brightyellow\">description</span>");
	}

	private Course getCourse() {
		Course course = new Course();
		course.setDescription(A_DESCRIPTION);

		return course;
	}
}
