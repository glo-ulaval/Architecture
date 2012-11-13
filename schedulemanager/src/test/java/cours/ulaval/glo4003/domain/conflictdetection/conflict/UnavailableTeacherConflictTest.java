package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot;

public class UnavailableTeacherConflictTest {

	private static final String A_NRC = "11234";
	private static final String A_TEACHER = "Derp McDerpson";

	@Test
	public void canInstantiateConflictWithParam() {
		TimeSlot timeSlot = mock(TimeSlot.class);
		UnavailableTeacherConflict conflict = new UnavailableTeacherConflict(A_NRC, A_TEACHER, timeSlot);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(A_TEACHER, conflict.getTeacher());
		assertEquals(timeSlot, conflict.getFirstTimeSlot());
		assertEquals(40, conflict.getScore());
	}

}
