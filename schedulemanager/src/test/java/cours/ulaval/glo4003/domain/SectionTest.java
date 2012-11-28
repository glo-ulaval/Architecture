package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;
import cours.ulaval.glo4003.persistence.XMLProgramSheetRepository;

public class SectionTest {
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;

	private String nrc;
	private String group;
	private String personInCharge;
	private List<String> teachers;
	private TimeDedicated timeDedicated;
	private TeachMode teachMode;
	private List<TimeSlot> courseTimeSlot;
	private TimeSlot labTimeSlot;
	private String courseAcronym;
	private Section section;

	@Before
	public void setUp() {
		nrc = "87134";
		group = "A";
		personInCharge = "a responsable person";
		teachers = Arrays.asList("teacher1", "teacher2");
		timeDedicated = new TimeDedicated();
		teachMode = TeachMode.InCourse;
		courseTimeSlot = Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY));
		labTimeSlot = new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.FRIDAY);
		courseAcronym = "GLO_4002";
		section = new Section(nrc, group, personInCharge, teachers, teachMode, timeDedicated, courseAcronym, courseTimeSlot,
				labTimeSlot);
	}

	@Test
	public void canInstantiateSectionWithParameters() {
		assertEquals(nrc, section.getNrc());
		assertEquals(group, section.getGroup());
		assertEquals(personInCharge, section.getPersonInCharge());
		assertEquals(teachers, section.getTeachers());
		assertEquals(timeDedicated, section.getTimeDedicated());
		assertEquals(teachMode, section.getTeachMode());
		assertEquals(courseTimeSlot, section.getCourseTimeSlots());
		assertEquals(labTimeSlot, section.getLabTimeSlot());
		assertEquals(courseAcronym, section.getCourseAcronym());
	}

	@Test
	public void canGetCourseAndLabTimeSlots() {
		List<TimeSlot> timeSlots = section.getCoursesAndLabTimeSlots();

		assertEquals(2, timeSlots.size());
	}

	@Test
	public void hasTeacherShouldReturnTrueIfTeacherIsInTeachers() {
		assertTrue(section.hasTeacher("teacher1"));
	}

	@Test
	public void areSameLevelShouldReturnTrueIfCourseAreSameLevel() {
		ProgramSheet programSheetMock = mock(ProgramSheet.class);
		when(programSheetMock.areCoursesSameLevel(anyString(), anyString())).thenReturn(true);
		XMLProgramSheetRepository repositoryMock = mock(XMLProgramSheetRepository.class);
		when(repositoryMock.getProgramSheetGLO()).thenReturn(programSheetMock);
		when(repositoryMock.getProgramSheetIFT()).thenReturn(programSheetMock);
		section.setProgramSheetRepository(repositoryMock);

		assertTrue(section.areSameLevel(section));

		verify(programSheetMock, times(2)).areCoursesSameLevel(anyString(), anyString());
		verify(repositoryMock).getProgramSheetGLO();
		verify(repositoryMock).getProgramSheetIFT();
	}

	@Test
	public void areConcomittingShouldReturnTrueIfCourseAreSameLevel() {
		Course courseMock = mock(Course.class);
		when(courseMock.isConcomitting(any(Course.class))).thenReturn(true);
		Course anotherCourseMock = mock(Course.class);
		when(anotherCourseMock.isConcomitting(any(Course.class))).thenReturn(false);
		XMLCourseRepository repositoryMock = mock(XMLCourseRepository.class);
		when(repositoryMock.findByAcronym(anyString())).thenReturn(courseMock, anotherCourseMock);
		section.setCourseRepository(repositoryMock);

		assertTrue(section.areConcomitting(section));

		verify(courseMock).isConcomitting(any(Course.class));
		verify(repositoryMock, times(2)).findByAcronym(anyString());
	}

	@Test
	public void canCloneASection() {
		Section clonedSection = section.clone();

		assertEquals(nrc, clonedSection.getNrc());
		assertEquals(group, clonedSection.getGroup());
		assertEquals(personInCharge, clonedSection.getPersonInCharge());
		assertEquals(teachers, clonedSection.getTeachers());
		assertEquals(timeDedicated, clonedSection.getTimeDedicated());
		assertEquals(teachMode, clonedSection.getTeachMode());
		assertEquals(courseTimeSlot.size(), clonedSection.getCourseTimeSlots().size());
		assertEquals(labTimeSlot.getStartTime(), clonedSection.getLabTimeSlot().getStartTime());
		assertEquals(courseAcronym, clonedSection.getCourseAcronym());
	}

	@Test
	public void canTellIfASectionIsSupposedToHaveLabTimeSlot() {
		TimeDedicated timeDedicated = new TimeDedicated(3, 2, 4);
		section.setTimeDedicated(timeDedicated);

		boolean result = section.isSupposedToHaveLab();

		assertTrue(result);
	}

	@Test
	public void canTellIfASectionIsNotSupposedToHaveLabTimeSlot() {
		TimeDedicated timeDedicated = new TimeDedicated(3, 0, 6);
		section.setTimeDedicated(timeDedicated);

		boolean result = section.isSupposedToHaveLab();

		assertFalse(result);
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}

}
