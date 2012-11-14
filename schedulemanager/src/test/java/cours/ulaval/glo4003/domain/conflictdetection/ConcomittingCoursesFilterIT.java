package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.persistence.ITTestBase;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;

public class ConcomittingCoursesFilterIT extends ITTestBase {
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;

	private CourseRepository courseRepository;
	private Section glo2002Section;
	private Section ift2004Section;
	private Section ift2002Section;

	@Before
	public void setup()
			throws Exception {
		Prerequisite concomittingPrerequisite = new Prerequisite();
		concomittingPrerequisite.setAcronyms(Arrays.asList("IFT-2002"));
		concomittingPrerequisite.setIsConcomitant(true);

		Prerequisite notConcomittingPrerequisite = new Prerequisite();
		notConcomittingPrerequisite.setAcronyms(Arrays.asList("IFT-2002"));
		notConcomittingPrerequisite.setIsConcomitant(false);

		Course glo2002 = new Course("GLO-2002", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier,
				Arrays.asList(concomittingPrerequisite), new TimeDedicated(3, 0, 6));
		Course ift2004 = new Course("IFT-2004", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier,
				Arrays.asList(notConcomittingPrerequisite), new TimeDedicated(3, 0, 6));
		Course ift2002 = new Course("IFT-2002", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier,
				new ArrayList<Prerequisite>(), new TimeDedicated(3, 0, 6));

		courseRepository = new XMLCourseRepository();

		courseRepository.store(glo2002);
		courseRepository.store(ift2004);
		courseRepository.store(ift2002);

		glo2002Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "GLO-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2004Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2004", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);
		ift2002Section = new Section("11765", "A", "a responsable person", Arrays.asList("teacher1", "teacher2"),
				TeachMode.InCourse, new TimeDedicated(), "IFT-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3,
						DayOfWeek.MONDAY)), null);

	}

	@After
	public void tearDown() {
		courseRepository.clear();
	}

	@Test
	public void shouldDetectConcomittingCoursesConflict()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2002Section);
		schedule.add(glo2002Section);

		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();
		filter.setRepository(courseRepository);

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("IFT-2002", schedule.getConflicts().get(0).getFirstNrc());
	}

	@Test
	public void shouldNotDetectConflictWhenCoursesNotConcomitting()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(ift2002Section);
		schedule.add(ift2004Section);

		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();
		filter.setRepository(courseRepository);

		filter.run(schedule);

		assertEquals(0, schedule.getConflicts().size());
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
