package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class TimeSlotTest {

	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;
	private static final int A_DURATION = 3;

	@Test
	public void canInstantiateTimeSlotWithStartTimeAndDuration() {
		Time startTime = generateStartTime();
		Integer duration = A_DURATION;

		TimeSlot timeSlot = new TimeSlot(startTime, duration, DayOfWeek.MONDAY);

		assertNotNull(timeSlot);
		assertEquals(startTime, timeSlot.getStartTime());
		assertEquals(duration, timeSlot.getDuration());
	}

	@Test
	public void shouldCalculateCorrespondingEndTimeOnInstantiation() {
		Time startTime = generateStartTime();
		Integer duration = A_DURATION;
		Time endTime = generateEndTime();

		TimeSlot timeSlot = new TimeSlot(startTime, duration, DayOfWeek.MONDAY);

		assertEquals(endTime.getHour(), timeSlot.getEndTime().getHour());
		assertEquals(endTime.getMinute(), timeSlot.getEndTime().getMinute());
	}

	private Time generateEndTime() {
		Time endTime = new Time();
		endTime.setHour(A_HOUR + A_DURATION);
		endTime.setMinute(A_MINUTE);
		return endTime;
	}

	private Time generateStartTime() {
		Time startTime = new Time();
		startTime.setHour(A_HOUR);
		startTime.setMinute(A_MINUTE);
		return startTime;
	}

}