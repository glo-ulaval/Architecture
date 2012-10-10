package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;

public class ScheduleXMLWrapperTest {
	@Test
	public void canGetSchedulesCountWithNoCourse() {
		ScheduleXMLWrapper dto = new ScheduleXMLWrapper();

		assertEquals(0, dto.getSchedules().size());
	}

	@Test
	public void canGetSchedulesCountWithCourses() {
		ScheduleXMLWrapper dto = new ScheduleXMLWrapper();
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
