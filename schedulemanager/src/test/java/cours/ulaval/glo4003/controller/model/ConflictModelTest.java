package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConflictWithTeacher;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;

public class ConflictModelTest {

	private static final String A_NRC = "98765";
	private static final String A_SECOND_NRC = "12345";
	private static final String A_TEACHER = "Hello this is dog";
	private TimeSlot firstTimeSlot;
	private TimeSlot secondTimeSlot;

	@Before
	public void setUp() {
		Time time = new Time(8, 30);
		firstTimeSlot = new TimeSlot(time, 2, DayOfWeek.MONDAY);
		secondTimeSlot = new TimeSlot(time, 3, DayOfWeek.FRIDAY);
	}

	@Test
	public void canBuildConflictModelFromConflict() {
		Conflict conflict = prepareConflictWithTeacher();

		ConflictModel model = new ConflictModel(conflict);

		assertEquals(model.getDescription(), conflict.getDescription());
		assertEquals(model.getFirstNrc(), conflict.getFirstNrc());
		assertEquals(model.getDayOfWeek(), firstTimeSlot.getDayOfWeek().toString());
		assertEquals(model.getFirstStartTime(), firstTimeSlot.getStartTime().toString());
		assertEquals(model.getFirstEndTime(), firstTimeSlot.getEndTime().toString());
	}

	@Test
	public void canBuildConflictModelFromConflictWithTeacher() {
		ConflictWithTeacher conflict = prepareConflictWithTeacher();

		ConflictModel model = new ConflictModel(conflict);

		assertEquals(model.getTeacher(), conflict.getTeacher());
	}

	@Test
	public void canBuildConflictModelFromConflictWithTwoTimeSlots() {
		Conflict conflict = prepareConflictWithTwoSlots();

		ConflictModel model = new ConflictModel(conflict);

		assertEquals(model.getSecondNrc(), conflict.getSecondNrc());
		assertEquals(model.getSecondStartTime(), secondTimeSlot.getStartTime().toString());
		assertEquals(model.getSecondEndTime(), secondTimeSlot.getEndTime().toString());
	}

	private ConflictWithTeacher prepareConflictWithTeacher() {
		return new UnavailableTeacherConflict(A_NRC, A_TEACHER, firstTimeSlot);
	}

	private Conflict prepareConflictWithTwoSlots() {
		return new ConcomittingCoursesConflict(A_NRC, A_SECOND_NRC, firstTimeSlot, secondTimeSlot);
	}
}
