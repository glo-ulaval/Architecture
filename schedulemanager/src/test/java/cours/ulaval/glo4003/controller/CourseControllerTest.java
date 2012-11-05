package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class CourseControllerTest {

	private final String AN_ACRONYM = "IFT-2002";

	@Mock
	private CourseRepository courseRepository;
	private Course course;

	@InjectMocks
	private CourseController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		course = getCourseForTest();
		when(courseRepository.findByAcronym(AN_ACRONYM)).thenReturn(getCourseForTest());
	}

	@Test
	public void getDescriptionGetsCourseFromRepository() {
		controller.getDescription(AN_ACRONYM);

		verify(courseRepository, times(1)).findByAcronym(AN_ACRONYM);
	}

	@Test
	public void getDescriptionReturnsTheCorrectModelAndView() {
		ModelAndView mv = controller.getDescription(AN_ACRONYM);

		assertEquals("coursedescription", mv.getViewName());
		assertEquals(course.getAcronym(), mv.getModel().get("acronym"));
		assertEquals(course.getTitle(), mv.getModel().get("title"));
		assertEquals(course.getDescription(), mv.getModel().get("description"));
		assertEquals(course.getCycle(), mv.getModel().get("cycle"));
	}

	@Test
	public void getDescriptionReturnsTheCorrectTimeDedicated() {
		ModelAndView mv = controller.getDescription(AN_ACRONYM);

		assertEquals(course.getTimeDedicated().getCourseHours(), mv.getModel().get("timeInClass"));
		assertEquals(course.getTimeDedicated().getLabHours(), mv.getModel().get("timeInLab"));
		assertEquals(course.getTimeDedicated().getOtherHours(), mv.getModel().get("timeAtHome"));
	}

	@Test
	public void getDescriptionReturnsTheCorrectPrerequisites() {
		ModelAndView mv = controller.getDescription(AN_ACRONYM);

		assertEquals(course.getPrerequisites(), mv.getModel().get("prerequisites"));
	}

	private Course getCourseForTest() {
		Prerequisite prerequisite = new Prerequisite();
		prerequisite.setAcronyms(Arrays.asList(AN_ACRONYM));
		TimeDedicated timeDedicated = new TimeDedicated(1, 2, 3);
		Course course = new Course();
		course.setAcronym(AN_ACRONYM);
		course.setTimeDedicated(timeDedicated);
		course.setTitle("This is a title");
		course.setDescription("This is a description");
		course.setCycle(Cycle.Deuxieme);
		course.setPrerequisites(Arrays.asList(prerequisite));

		return course;
	}
}
