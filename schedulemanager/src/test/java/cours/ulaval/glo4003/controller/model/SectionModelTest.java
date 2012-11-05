package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class SectionModelTest {

	private final static Integer START_HOURS = 16;
	private final static Integer START_MIN = 30;
	private final static Integer END_HOURS = 18;
	private final static Integer END_MIN = 30;
	private final static String A_DAY = "Lundi";

	private SectionModel sectionModel = new SectionModel();

	@Before
	public void setUp() throws Exception {
		sectionModel = new SectionModel();
		sectionModel.setAcronym("");
		sectionModel.setGroup("");
		sectionModel.setPersonInCharge("");
		sectionModel.setTeachers(new ArrayList<String>());
		sectionModel.setTeachMode("Virtual");
		sectionModel.setHoursAtHome(2);
		sectionModel.setHoursInClass(2);
		sectionModel.setHoursInLab(2);
	}

	@Test
	public void canConvertLabTimeSlot() {
		sectionModel.setLaboTimeSlotStart(START_HOURS + ":" + START_MIN);
		sectionModel.setLaboTimeSlotEnd(END_HOURS + ":" + END_MIN);
		sectionModel.setLabDay(A_DAY);

		Section section = sectionModel.convertToSection();
		TimeSlot timeSlot = section.getLabTimeSlot();
		Time tStartConverted = timeSlot.getStartTime();
		Time tStartExpected = new Time(START_HOURS, START_MIN);

		assertTrue(tStartConverted.equals(tStartExpected));
	}

	@Test
	public void canConvertTimeSlots() {
		List<String> days = new ArrayList<String>();
		days.add(DayOfWeek.MONDAY.toString());
		sectionModel.setDays(days);

		List<String> timeSlotStarts = new ArrayList<String>();
		timeSlotStarts.add(START_HOURS + ":" + START_MIN);
		sectionModel.setTimeSlotStarts(timeSlotStarts);

		List<String> timeSlotEnds = new ArrayList<String>();
		timeSlotEnds.add(END_HOURS + ":" + END_MIN);
		sectionModel.setTimeSlotEnds(timeSlotEnds);

		Section section = sectionModel.convertToSection();
		List<TimeSlot> timeSlots = section.getCourseTimeSlots();
		Time tStartConverted = timeSlots.get(0).getStartTime();
		Time tStartExpected = new Time(START_HOURS, START_MIN);

		assertTrue(tStartConverted.equals(tStartExpected));
	}

	@Test
	public void canGetBasicInformationWhenInstantiateSectionModelFromSection() {
		Section section = createSection();
		SectionModel model = new SectionModel(section);

		assertEquals(section.getNrc(), model.getNrc());
		assertEquals(section.getGroup(), model.getGroup());
		assertEquals(section.getPersonInCharge(), model.getPersonInCharge());
		assertEquals(section.getTeachers(), model.getTeachers());
		assertEquals(section.getTeachMode().toString(), model.getTeachMode());
		assertEquals(section.getCourseAcronym(), model.getAcronym());
	}

	@Test
	public void canGetTimeDedicatedWhenInstantiateSectionModelFromSection() {
		Section section = createSection();
		SectionModel model = new SectionModel(section);

		TimeDedicated timeDedicated = section.getTimeDedicated();
		assertEquals(timeDedicated.getCourseHours(), (int) model.getHoursInClass());
		assertEquals(timeDedicated.getLabHours(), (int) model.getHoursInLab());
		assertEquals(timeDedicated.getOtherHours(), (int) model.getHoursAtHome());
	}

	@Test
	public void canGetLabTimeSlotWhenInstantiateSectionModelFromSection() {
		Section section = createSection();
		SectionModel model = new SectionModel(section);

		TimeSlot labTimeSlot = section.getLabTimeSlot();
		assertEquals(labTimeSlot.getStartTime().toString(), model.getLaboTimeSlotStart());
		assertEquals(labTimeSlot.getEndTime().toString(), model.getLaboTimeSlotEnd());
		assertEquals("Vendredi", model.getLabDay());
	}

	@Test
	public void canGetTimeSlotsWhenInstantiateSectionModelFromSection() {
		Section section = createSection();
		SectionModel model = new SectionModel(section);

		assertEquals(1, model.getTimeSlotStarts().size());
		assertEquals(1, model.getTimeSlotEnds().size());
		assertEquals(1, model.getDays().size());
	}

	private Section createSection() {
		Section section = new Section();
		section.setNrc("00000");
		section.setTimeDedicated(new TimeDedicated(2, 3, 4));
		section.setTeachMode(TeachMode.InCourse);
		Time time = new Time(8, 0);
		TimeSlot slot = new TimeSlot(time, 2, DayOfWeek.FRIDAY);
		section.setLabTimeSlot(slot);
		section.setCourseTimeSlots(Arrays.asList(slot));

		return section;
	}
}
