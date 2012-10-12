package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Time;
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
		sectionModel.setLabDays(A_DAY);

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
}
