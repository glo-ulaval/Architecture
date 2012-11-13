package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot;

public class DisponibilityConflictTest {

	private static final String A_NRC = "11234";
	private static final String A_TEACHER = "Derp McDerpson";

	@Test
	public void canInstantiateDisponibilityConflictWithParam() {
		TimeSlot timeSlot = mock(TimeSlot.class);
		DisponibilityConflict conflict = new DisponibilityConflict(A_NRC, A_TEACHER, timeSlot);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(A_TEACHER, conflict.getTeacher());
		assertEquals(timeSlot, conflict.getFirstTimeSlot());
		assertEquals(10, conflict.getScore());
	}
}
