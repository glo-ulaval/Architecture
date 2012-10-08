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

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;

public class OfferingControllerTest {
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
		when(mockedOfferingRepository.find(anyString())).thenReturn(mockedOffering);
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
	public void offeringByYearReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.offeringByYear("2012");

		verify(mockedOfferingRepository).find("2012");
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbyyear", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void deleteCourseReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.deleteCourse("2012", "GLO-4003");

		verify(mockedOfferingRepository).find("2012");
		verify(mockedOffering).removeCourse("GLO-4003");
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbyyear", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void availableCoursesReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.availableCourses("2012");

		verify(mockedCourseRepository).findAll();
		assertEquals("availablecourses", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("courses"));
	}

	@Test
	public void addCourseFromAvailableCoursesReturnsTheCorrectModelAndView()
			throws Exception {

		ModelAndView mv = controller.addCourseFromAvailableCourses("2012", "GLO-4003");

		verify(mockedOfferingRepository).find("2012");
		verify(mockedOffering).addCourse("GLO-4003");
		verify(mockedCourseRepository).findByOffering(any(Offering.class));
		assertEquals("offeringbyyear", mv.getViewName());
		assertTrue(mv.getModel().containsKey("year"));
		assertTrue(mv.getModel().containsKey("courses"));
	}
}