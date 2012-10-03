package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TimeSlotTest {

	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;
	private static final int A_DURATION = 3;

	@Test
	public void canInstantiateTimeSlotWithStartTimeAndDuration() {
		Calendar startTime = generateStartTime();
		Integer duration = A_DURATION;

		TimeSlot timeSlot = new TimeSlot(startTime, duration);

		assertNotNull(timeSlot);
		assertEquals(startTime, timeSlot.getStartTime());
		assertEquals(duration, timeSlot.getDuration());
	}

	@Test
	public void shouldCalculateCorrespondingEndTimeOnInstantiation() {
		Calendar startTime = generateStartTime();
		Integer duration = A_DURATION;
		Calendar endTime = generateEndTime();

		TimeSlot timeSlot = new TimeSlot(startTime, duration);

		assertEquals(endTime.get(Calendar.HOUR), timeSlot.getEndTime().get(Calendar.HOUR));
		assertEquals(endTime.get(Calendar.MINUTE), timeSlot.getEndTime().get(Calendar.MINUTE));
	}

	private Calendar generateEndTime() {
		Calendar endTime = Calendar.getInstance();
		endTime.set(Calendar.HOUR, A_HOUR + A_DURATION);
		endTime.set(Calendar.MINUTE, A_MINUTE);
		return endTime;
	}

	private Calendar generateStartTime() {
		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		startTime.set(Calendar.HOUR, A_HOUR);
		startTime.set(Calendar.MINUTE, A_MINUTE);
		return startTime;
	}

}