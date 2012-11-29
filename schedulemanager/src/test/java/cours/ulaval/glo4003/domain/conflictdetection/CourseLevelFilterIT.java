package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameLevelCourseConflict;
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
	private Filter nextFilterMock;

	@Before
	public void setup()
			throws Exception {
		programSheetRepository = new XMLProgramSheetRepository();

		glo1901Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "GLO-1901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		glo1901Section.setProgramSheetRepository(programSheetRepository);
		glo1010Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "GLO-1010", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		glo1010Section.setProgramSheetRepository(programSheetRepository);
		ift2002Section = new Section("11765", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2002Section.setProgramSheetRepository(programSheetRepository);
		ift1000Section = new Section("21345", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-1000", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift1000Section.setProgramSheetRepository(programSheetRepository);
		ift2901Section = new Section("87678", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2901Section.setProgramSheetRepository(programSheetRepository);

		nextFilterMock = mock(Filter.class);
	}

	@Test
	public void shouldDetectSameCourseLevelConflictInGLOProgramSheet()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1010Section);
		schedule.add(glo1901Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule);

		assertEquals(1, conflicts.size());
		assertEquals("90876", conflicts.get(0).getFirstNrc());
		assertEquals("87134", conflicts.get(0).getSecondNrc());
		assertTrue(conflicts.get(0) instanceof SameLevelCourseConflict);
		verify(nextFilterMock).run(schedule);
	}

	@Test
	public void shouldDetectSameCourseLevelConflictInIFTProgramSheet()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2002Section);
		schedule.add(ift1000Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule);

		assertEquals(1, conflicts.size());
		assertEquals("11765", conflicts.get(0).getFirstNrc());
		assertEquals("21345", conflicts.get(0).getSecondNrc());
		assertTrue(conflicts.get(0) instanceof SameLevelCourseConflict);
		verify(nextFilterMock).run(schedule);
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
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule);

		assertEquals(0, conflicts.size());
		verify(nextFilterMock).run(schedule);
	}

	@Test
	public void shouldDetectSameCourseLevelConflictInGLOProgramSheetForSection()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1010Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule, glo1901Section);

		assertEquals(1, conflicts.size());
		assertEquals("87134", conflicts.get(0).getFirstNrc());
		assertEquals("90876", conflicts.get(0).getSecondNrc());
		assertTrue(conflicts.get(0) instanceof SameLevelCourseConflict);
		verify(nextFilterMock).run(schedule, glo1901Section);
	}

	@Test
	public void shouldDetectSameCourseLevelConflictInIFTProgramSheetForSection()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2002Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule, ift1000Section);

		assertEquals(1, conflicts.size());
		assertEquals("21345", conflicts.get(0).getFirstNrc());
		assertEquals("11765", conflicts.get(0).getSecondNrc());
		assertTrue(conflicts.get(0) instanceof SameLevelCourseConflict);
		verify(nextFilterMock).run(schedule, ift1000Section);
	}

	@Test
	public void shouldNotDetectConflictWhenCoursesAreNotSameLevelForSection()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2901Section);
		schedule.add(glo1901Section);

		CourseLevelFilter filter = new CourseLevelFilter();
		filter.setRepository(programSheetRepository);
		filter.connectToFilter(nextFilterMock);

		List<Conflict> conflicts = filter.run(schedule, ift2002Section);

		assertEquals(0, conflicts.size());
		verify(nextFilterMock).run(schedule, ift2002Section);
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
