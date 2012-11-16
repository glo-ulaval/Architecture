package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;
import cours.ulaval.glo4003.persistence.ITTestBase;

public class SameTeacherFilterIT extends ITTestBase {
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;

	private Section glo1901Section;
	private Section glo1010Section;
	private Section glo2000Section;

	@Before
	public void setup()
			throws Exception {
		glo1901Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-1901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)),
				null);
		glo1010Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-1010", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)),
				null);
		glo2000Section = new Section("09088", "A", "a responsable person", Arrays.asList("teacher2"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-2000", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)),
				null);
	}

	@Test
	public void shouldDetectConflictWhenSectionsHaveSameTeachers()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1901Section);
		schedule.add(glo1010Section);

		SameTeacherFilter filter = new SameTeacherFilter();

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("90876", schedule.getConflicts().get(0).getFirstNrc());
		assertTrue(schedule.getConflicts().get(0) instanceof SameTeacherConflict);
	}

	@Test
	public void shouldNotDetectConflictWhenSectionsHaveDifferentTeachers()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1901Section);
		schedule.add(glo2000Section);

		SameTeacherFilter filter = new SameTeacherFilter();

		filter.run(schedule);

		assertEquals(0, schedule.getConflicts().size());
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
