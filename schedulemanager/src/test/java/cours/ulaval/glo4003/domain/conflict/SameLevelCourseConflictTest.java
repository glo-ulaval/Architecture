package cours.ulaval.glo4003.domain.conflict;

import static org.junit.Assert.*;

import org.junit.Test;

public class SameLevelCourseConflictTest {
	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateConflictWithParam() {
		SameLevelCourseConflict conflict = new SameLevelCourseConflict(A_NRC, ANOTHER_NRC);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(50, conflict.getScore());
	}
}
