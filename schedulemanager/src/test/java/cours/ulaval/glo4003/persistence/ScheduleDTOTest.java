package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.model.Schedule;

public class ScheduleDTOTest {
	@Test
	public void canGetSchedulesCountWithNoCourse() {
		ScheduleDTO dto = new ScheduleDTO();

		assertEquals(0, dto.getSchedules().size());
	}

	@Test
	public void canGetSchedulesCountWithCourses() {
		ScheduleDTO dto = new ScheduleDTO();
		dto.setSchedules(getSchedules());

		assertEquals(1, dto.getSchedules().size());
	}

	private List<Schedule> getSchedules() {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		Schedule schedule = mock(Schedule.class);
		schedules.add(schedule);

		return schedules;
	}
}
