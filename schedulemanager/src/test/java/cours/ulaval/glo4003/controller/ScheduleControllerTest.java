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

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;

public class ScheduleControllerTest {

	private final String AN_ACRONYM = "IFT-2002";
	private final String A_YEAR = "2012";

	@Mock
	private CourseRepository mockedCourseRepository;
	@Mock
	private OfferingRepository mockedOfferingRepository;

	@InjectMocks
	private ScheduleController controller;

	private Course course;
	private List<Course> courses;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		course = mock(Course.class);
		Offering offering = mock(Offering.class);
		List<String> years = Arrays.asList(A_YEAR);
		courses = Arrays.asList(course);
		when(mockedOfferingRepository.findYears()).thenReturn(years);
		when(mockedOfferingRepository.find(A_YEAR)).thenReturn(offering);
		when(mockedCourseRepository.findByOffering(offering)).thenReturn(courses);
		when(mockedCourseRepository.findByAcronym(AN_ACRONYM)).thenReturn(course);
	}

	@Test
	public void canInstantiateController() {
		assertNotNull(controller);
	}

	@Test
	public void scheduleByYearReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.scheduleByYear(A_YEAR);

		assertEquals(A_YEAR, mv.getModel().get("year"));
	}

	@Test
	public void addScheduleReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.addSchedule();

		assertEquals(mockedOfferingRepository.findYears(), mv.getModel().get("years"));
	}

	@Test
	public void addScheduleWithYearReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.addSchedule(A_YEAR);

		assertEquals(A_YEAR, mv.getModel().get("year"));
		assertEquals(courses, mv.getModel().get("courses"));
	}

	@Test
	public void addSectionReturnsTheCorrectModelAndView() {
		ModelAndView mv = controller.addSection(A_YEAR, AN_ACRONYM);

		assertEquals(AN_ACRONYM, mv.getModel().get("acronym"));
		assertEquals(course, mv.getModel().get("course"));
	}
}
