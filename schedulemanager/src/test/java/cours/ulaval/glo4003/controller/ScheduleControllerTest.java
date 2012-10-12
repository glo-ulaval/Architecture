package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.SectionModel;
import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;

public class ScheduleControllerTest {

	private final String AN_ACRONYM = "IFT-2002";
	private final String A_YEAR = "2012";
	private final Semester A_SEMESTER = Semester.Automne;
	private final String A_SCHEDULE_ID = "ScheduleId";

	@Mock
	private CourseRepository mockedCourseRepository;
	@Mock
	private OfferingRepository mockedOfferingRepository;
	@Mock
	private ScheduleRepository mockedScheduleRepository;

	@InjectMocks
	private ScheduleController controller;

	private Course course;
	private List<Course> courses;
	private Schedule schedule;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		course = mock(Course.class);
		schedule = mock(Schedule.class);
		Offering offering = mock(Offering.class);
		List<String> years = Arrays.asList(A_YEAR);
		courses = Arrays.asList(course);
		when(mockedOfferingRepository.findYears()).thenReturn(years);
		when(mockedOfferingRepository.find(A_YEAR, A_SEMESTER)).thenReturn(offering);
		when(mockedCourseRepository.findByOffering(offering)).thenReturn(courses);
		when(mockedCourseRepository.findByAcronym(AN_ACRONYM)).thenReturn(course);
		when(mockedScheduleRepository.findById(A_SCHEDULE_ID)).thenReturn(schedule);
	}

	@Test
	public void canInstantiateController() {
		assertNotNull(controller);
	}

	@Test
	public void scheduleByYearReturnsTheCorrectModelAndView()
			throws Exception {
		ModelAndView mv = controller.scheduleById(A_SCHEDULE_ID);

		assertEquals(schedule, mv.getModel().get("schedule"));
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
		ModelAndView mv = controller.addSchedule(A_YEAR, A_SEMESTER);

		assertEquals(A_YEAR, mv.getModel().get("year"));
		assertEquals(courses, mv.getModel().get("courses"));
	}

	@Test
	public void addSectionReturnsTheCorrectModelAndView() {
		ModelAndView mv = controller.addSection(A_SCHEDULE_ID, A_YEAR, A_SEMESTER, AN_ACRONYM);

		assertEquals(AN_ACRONYM, mv.getModel().get("acronym"));
		assertEquals(course, mv.getModel().get("course"));
		assertEquals(A_YEAR, mv.getModel().get("year"));
		assertEquals(A_SEMESTER, mv.getModel().get("semester"));
		assertEquals(A_SCHEDULE_ID, mv.getModel().get("id"));
	}

	@Test
	public void canPostASection()
			throws Exception {
		SectionModel model = mock(SectionModel.class);
		Section section = mock(Section.class);
		when(model.convertToSection()).thenReturn(section);
		doNothing().when(schedule).add(section);
		Map<String, Section> sections = new HashMap<String, Section>();
		sections.put(A_SCHEDULE_ID, section);
		when(schedule.getSections()).thenReturn(sections);

		ModelAndView mv = controller.postSection(A_SCHEDULE_ID, A_YEAR, A_SEMESTER, model);
		verify(schedule).add(section);

		assertEquals(A_YEAR, mv.getModel().get("year"));
		assertEquals(courses, mv.getModel().get("courses"));
		assertEquals(A_SEMESTER, mv.getModel().get("semester"));
		assertEquals(A_SCHEDULE_ID, mv.getModel().get("id"));
		assertTrue(mv.getModel().containsKey("sections"));
	}
}
