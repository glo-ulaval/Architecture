package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;

public class XMLScheduleRepositoryTest {

	private static int NB_OF_SCHEDULE = 10;
	private static String AN_ID = "anId";

	private XMLScheduleRepository scheduleRepo;
	private Map<String, Schedule> schedules;

	@Before
	public void setUp() throws Exception {
		scheduleRepo = new XMLScheduleRepository();
	}

	@Test
	public void canFindAll() {
		addAFewSchedule();

		assertEquals(NB_OF_SCHEDULE, scheduleRepo.findAll().size());
	}

	@Test
	public void canFindByYear() {
		addTwoScheduleWithTwoDifferentYear();

		assertEquals(1, scheduleRepo.findBy("2012").size());
	}

	@Test
	public void canFindById() throws Exception {
		Schedule schedule = mock(Schedule.class);
		when(schedule.getId()).thenReturn(AN_ID);
		scheduleRepo.store(schedule);

		assertEquals(schedule, scheduleRepo.findById(AN_ID));
	}

	@Test
	public void canStoreASchedule() throws Exception {
		Schedule schedule = mock(Schedule.class);
		when(schedule.getId()).thenReturn(AN_ID);

		scheduleRepo.store(schedule);

		assertEquals(schedule, scheduleRepo.findAll().get(0));
	}

	@Test
	public void cannotStoreADuplicateSchedule() throws Exception {
		Schedule schedule = mock(Schedule.class);
		when(schedule.getId()).thenReturn(AN_ID);

		scheduleRepo.store(schedule);
		scheduleRepo.store(schedule);

		assertEquals(1, scheduleRepo.findAll().size());
	}

	@Test
	public void canDeleteASchedule() throws Exception {
		Schedule schedule = mock(Schedule.class);
		when(schedule.getId()).thenReturn(AN_ID);

		scheduleRepo.store(schedule);
		scheduleRepo.delete(AN_ID);

		assertEquals(0, scheduleRepo.findAll().size());
	}

	@Test
	public void doesntDeleteAnythingIfTheScheduleDoesntExist() throws Exception {
		Map<String, Schedule> schedules = mock(Map.class);
		when(schedules.containsKey(anyString())).thenReturn(false);

		scheduleRepo.delete("uneClé");

		verify(schedules, never()).remove(anyString());
	}

	private void addAFewSchedule() {
		schedules = new HashMap<String, Schedule>();
		for (Integer i = 0; i < NB_OF_SCHEDULE; i++) {
			Schedule schedule = mock(Schedule.class);
			when(schedule.getId()).thenReturn(AN_ID + i.toString());
			schedules.put(schedule.getId(), schedule);
		}

		scheduleRepo.setSchedules(schedules);
	}

	private void addTwoScheduleWithTwoDifferentYear() {
		schedules = new HashMap<String, Schedule>();

		Schedule schedule2011 = mock(Schedule.class);
		Schedule schedule2012 = mock(Schedule.class);
		when(schedule2011.getId()).thenReturn(AN_ID + "2011");
		when(schedule2012.getId()).thenReturn(AN_ID + "2012");
		when(schedule2011.getYear()).thenReturn("2011");
		when(schedule2012.getYear()).thenReturn("2012");

		schedules.put("2011", schedule2011);
		schedules.put("2012", schedule2012);

		scheduleRepo.setSchedules(schedules);
	}

}
