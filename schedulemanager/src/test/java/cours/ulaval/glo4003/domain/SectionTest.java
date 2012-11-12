package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

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
		labTimeSlot = new TimeSlot();
		courseAcronym = "GLO_4002";
		section = new Section(nrc, group, personInCharge, teachers, teachMode, timeDedicated, courseAcronym, courseTimeSlot, labTimeSlot);
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
	public void weCanFindATeacherInTheList() {
		assertTrue(section.hasTeacher("teacher1"));
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
