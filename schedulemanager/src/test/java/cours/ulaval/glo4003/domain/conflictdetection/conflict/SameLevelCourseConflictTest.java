package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot;

public class SameLevelCourseConflictTest {
	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateConflictWithParam() {
		TimeSlot firstTimeSlot = mock(TimeSlot.class);
		TimeSlot secondTimeSlot = mock(TimeSlot.class);
		SameLevelCourseConflict conflict = new SameLevelCourseConflict(A_NRC, ANOTHER_NRC, firstTimeSlot, secondTimeSlot);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(firstTimeSlot, conflict.getFirstTimeSlot());
		assertEquals(secondTimeSlot, conflict.getSecondTimeSlot());
		assertEquals(50, conflict.getScore());
	}
}
