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
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;
import cours.ulaval.glo4003.persistence.ITTestBase;
import cours.ulaval.glo4003.persistence.XMLProgramSheetRepository;

public class CourseLevelFilterIT extends ITTestBase {
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;

	private ProgramSheetRepository programSheetRepository;
	private Section glo1901Section;
	private Section glo1010Section;
	private Section ift2002Section;
	private Section ift1000Section;
	private Section ift2901Section;

	@Before
	public void setup()
			throws Exception {
		programSheetRepository = new XMLProgramSheetRepository();

		glo1901Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "GLO-1901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		glo1010Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "GLO-1010", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2002Section = new Section("11765", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift1000Section = new Section("21345", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-1000", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2901Section = new Section("87678", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);

	}

	@Test
	public void shouldDetectSameCourseLevelConflictInGLOProgramSheet()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1010Section);
		schedule.add(glo1901Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("GLO-1010", schedule.getConflicts().get(0).getFirstNrc());
		assertEquals("GLO-1901", schedule.getConflicts().get(0).getSecondNrc());
	}

	@Test
	public void shouldDetectSameCourseLevelConflictInIFTProgramSheet()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2002Section);
		schedule.add(ift1000Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("IFT-2002", schedule.getConflicts().get(0).getFirstNrc());
		assertEquals("IFT-1000", schedule.getConflicts().get(0).getSecondNrc());
	}

	@Test
	public void shouldNotDetectConflictWhenCoursesAreNotSameLevel()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2901Section);
		schedule.add(glo1901Section);
		schedule.add(ift2002Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);

		filter.run(schedule);

		assertEquals(0, schedule.getConflicts().size());
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
