package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TeachMode;
import cours.ulaval.glo4003.domain.Time;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.DisponibilityConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;
import cours.ulaval.glo4003.persistence.ITTestBase;
import cours.ulaval.glo4003.persistence.XMLAvailabilityRepository;

public class UnavailableTeacherFilterIT extends ITTestBase {
	private static final String JSON_STRING = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,2,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;
	private static final String A_IDUL = "teacher1";

	private static ObjectMapper mapper = new ObjectMapper();
	private static AvailabilityModel availabilityModel;
	private static Availability availabilities;

	private static AvailabilityRepository availabilityRepository;
	private Section glo1901Section;
	private Section glo1010Section;
	private Section glo2000Section;

	@BeforeClass
	public static void setupClass()
			throws Exception {
		availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);
		availabilities = availabilityModel.toAvailability(A_IDUL);

		availabilityRepository = new XMLAvailabilityRepository();
		availabilityRepository.store(availabilities);
	}

	@Before
	public void setup()
			throws Exception {

		glo1901Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-1901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)),
				null);
		glo1010Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-1010", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.TUESDAY)),
				null);
		glo2000Section = new Section("09088", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse,
				new TimeDedicated(), "GLO-2000", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.THURSDAY)),
				null);
	}

	@Test
	public void shouldDetectConflictWhenATeacherIsPreferedNotAvailable()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1010Section);

		UnavailableTeacherFilter filter = new UnavailableTeacherFilter();
		filter.setRepository(availabilityRepository);

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("90876", schedule.getConflicts().get(0).getFirstNrc());
		assertTrue(schedule.getConflicts().get(0) instanceof DisponibilityConflict);
	}

	@Test
	public void shouldDetectConflictWhenATeacherIsUnavailable()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo2000Section);

		UnavailableTeacherFilter filter = new UnavailableTeacherFilter();
		filter.setRepository(availabilityRepository);

		filter.run(schedule);

		assertEquals(1, schedule.getConflicts().size());
		assertEquals("09088", schedule.getConflicts().get(0).getFirstNrc());
		assertTrue(schedule.getConflicts().get(0) instanceof UnavailableTeacherConflict);
	}

	@Test
	public void shouldNotDetectConflictWhenTeacherIsAvailable()
			throws Exception {
		Schedule schedule = new Schedule("h2012");
		schedule.add(glo1901Section);

		UnavailableTeacherFilter filter = new UnavailableTeacherFilter();
		filter.setRepository(availabilityRepository);

		filter.run(schedule);

		assertEquals(0, schedule.getConflicts().size());
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}
}
