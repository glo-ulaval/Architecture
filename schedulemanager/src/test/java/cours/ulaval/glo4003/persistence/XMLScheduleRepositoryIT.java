package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;

public class XMLScheduleRepositoryIT extends ITTestBase {
	private static Schedule aSchedule;
	private static XMLScheduleRepository repository;
	private static Schedule anotherSchedule;

	@Before
	public void setUp()
			throws Exception {
		TimeSlot timeSlot = new TimeSlot(generateStartTime(), 3, DayOfWeek.MONDAY);
		TimeDedicated timeDedicated = generatedTimeDedicated();
		Section section = new Section("87135", "A", "Derp McDerp", Arrays.asList("Herp McCoy", "Patches O'Hoolahan"),
				TeachMode.InCourse, timeDedicated, "GLO-4003", Arrays.asList(timeSlot), new TimeSlot());
		Section anotherSection = new Section("90659", "A", "Herp McHerpington", Arrays.asList("Tom Seleck"), TeachMode.Virtual,
				timeDedicated, "IFT_2001", Arrays.asList(timeSlot), new TimeSlot());
		ConcomittingCoursesConflict conflict = new ConcomittingCoursesConflict("nrc1", "nrc2", timeSlot, timeSlot);

		aSchedule = new Schedule("DERP");
		aSchedule.setPersonInCharge("Nadia Tawbi");
		aSchedule.setYear("2011-2012");
		aSchedule.setSemester(Semester.Automne);
		aSchedule.add(section);
		aSchedule.add(anotherSection);
		aSchedule.add(conflict);

		anotherSchedule = new Schedule("ID");
		anotherSchedule.setPersonInCharge("Nadir Belkhiter");
		anotherSchedule.setYear("2012-2013");

		repository = new XMLScheduleRepository();
	}

	@After
	public void tearDown()
			throws Exception {
		repository.clearAll();
	}

	@Test
	public void canSaveASchedule()
			throws Exception {
		repository.store(aSchedule);

		XMLScheduleRepository anotherRepository = new XMLScheduleRepository();
		List<Schedule> schedules = anotherRepository.findAll();
		Schedule detachedSchedule = schedules.get(0);

		assertEquals(aSchedule.getId(), schedules.get(0).getId());
		assertTrue(detachedSchedule.getSections().containsKey("87135"));
		assertTrue(detachedSchedule.getSections().containsKey("90659"));
		assertEquals(1, detachedSchedule.getConflicts().size());
		assertTrue(detachedSchedule.getConflicts().get(0) instanceof ConcomittingCoursesConflict);
	}

	@Test
	public void canDeleteASchedule()
			throws Exception {
		repository.store(anotherSchedule);

		repository.delete("ID");

		XMLScheduleRepository anotherRepository = new XMLScheduleRepository();
		assertFalse(repository.findAll().contains(anotherSchedule));
		assertFalse(anotherRepository.findAll().contains(anotherSchedule));
	}

	@Test
	public void canFindAllSchedules()
			throws Exception {
		repository.store(aSchedule);
		repository.store(anotherSchedule);
		XMLScheduleRepository anotherRepository = new XMLScheduleRepository();

		List<Schedule> schedules = anotherRepository.findAll();

		assertEquals(2, schedules.size());
	}

	@Test
	public void canFindScheduleByYear()
			throws Exception {
		repository.store(aSchedule);
		repository.store(anotherSchedule);
		XMLScheduleRepository anotherRepository = new XMLScheduleRepository();

		List<Schedule> schedules = anotherRepository.findBy("2011-2012");

		assertEquals(1, schedules.size());
	}

	private static TimeDedicated generatedTimeDedicated() {
		TimeDedicated timeDedicated = new TimeDedicated();
		timeDedicated.setCourseHours(3);
		timeDedicated.setLabHours(0);
		timeDedicated.setOtherHours(6);
		return timeDedicated;
	}

	private static Time generateStartTime() {
		Time startTime = new Time(13, 30);
		return startTime;
	}
}
