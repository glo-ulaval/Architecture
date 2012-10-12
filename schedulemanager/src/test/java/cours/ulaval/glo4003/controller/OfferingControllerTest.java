package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;

public class OfferingControllerTest {
	private final Semester A_SEMESTER = Semester.Automne;

	@Mock
	CourseRepository mockedCourseRepository;
	@Mock
	OfferingRepository mockedOfferingRepository;
	@Mock
	Offering mockedOffering;
	@Mock
	Course mockedCourse;

	@InjectMocks
	private OfferingController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		List<String> acronyms = new ArrayList<String>();
		acronyms.add("GLO-4003");
		when(mockedOfferingRepository.find(anyString(), any(Semester.class))).thenReturn(mockedOffering);
		when(mockedOffering.getAcronyms()).thenReturn(acronyms);
		when(mockedCourseRepository.findByAcronym(anyString())).thenReturn(mockedCourse);
	}

	@Test
	public void canInstantiatAController() {
		assertNotNull(controller);
	}

	@Test
	public void offeringReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.offering();

		verify(mockedOfferingRepository).findYears();
		assertEquals("offering", mv.getViewName());
		assertTrue(mv.getModel().containsKey("years"));
	}

	@Test
	public void offeringBySemesterReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.offeringBySemester("2012", A_SEMESTER);

		verify(mockedOfferingRepository).find("2012", A_SEMESTER);
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbysemester", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertEquals(A_SEMESTER, mv.getModel().get("semester"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void deleteCourseReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.deleteCourse("2012", "Automne", "GLO-4003");

		verify(mockedOfferingRepository).find("2012", A_SEMESTER);
		verify(mockedOffering).removeCourse("GLO-4003");
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbysemester", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("semester"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void availableCoursesReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.availableCourses("2012", "Automne");

		verify(mockedCourseRepository).findAll();
		assertEquals("availablecourses", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("semester"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void addCourseFromAvailableCoursesReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.addCourseFromAvailableCourses("2012", "Automne", "GLO-4003");

		verify(mockedOfferingRepository).store(any(Offering.class));
		verify(mockedOfferingRepository).find("2012", A_SEMESTER);
		verify(mockedOffering).addCourse("GLO-4003");
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbysemester", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("semester"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void canAddCourseFromAvailableCoursesMultipleTimes()
			throws Exception {
		controller.addCourseFromAvailableCourses("2012", "Automne", "GLO-4003");
		controller.addCourseFromAvailableCourses("2012", "Automne", "GLO-4003");

		verify(mockedOfferingRepository, times(2)).find("2012", A_SEMESTER);
		verify(mockedOffering, times(2)).addCourse("GLO-4003");
		verify(mockedCourseRepository, times(2)).findByOffering(any(Offering.class));
	}
}