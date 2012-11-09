package cours.ulaval.glo4003.domain.conflict;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.DifferentLevelCourseConflict;

public class DifferentLevelCourseConflictTest {
	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateDisponibilityConflictWithParam() {
		DifferentLevelCourseConflict conflict = new DifferentLevelCourseConflict(A_NRC, ANOTHER_NRC);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(30, conflict.getScore());
	}
}
