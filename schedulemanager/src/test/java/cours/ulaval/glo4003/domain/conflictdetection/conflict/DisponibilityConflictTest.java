package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.DisponibilityConflict;

public class DisponibilityConflictTest {

	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateDisponibilityConflictWithParam() {
		DisponibilityConflict conflict = new DisponibilityConflict(A_NRC, ANOTHER_NRC);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(10, conflict.getScore());
	}
}
