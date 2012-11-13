package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot;

public class DifferentLevelCourseConflictTest {
	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateDisponibilityConflictWithParam() {
		TimeSlot firstTimeSlot = mock(TimeSlot.class);
		TimeSlot secondTimeSlot = mock(TimeSlot.class);
		DifferentLevelCourseConflict conflict = new DifferentLevelCourseConflict(A_NRC, ANOTHER_NRC, firstTimeSlot, secondTimeSlot);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(30, conflict.getScore());
		assertEquals(firstTimeSlot, conflict.getFirstTimeSlot());
		assertEquals(secondTimeSlot, conflict.getSecondTimeSlot());
	}
}
