package cours.ulaval.glo4003.domain.conflict;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConcomittingCoursesConflictTest extends Conflict {
	private static final String A_NRC = "11234";
	private static final String ANOTHER_NRC = "78123";

	@Test
	public void canInstantiateDisponibilityConflictWithParam() {
		ConcomittingCoursesConflict conflict = new ConcomittingCoursesConflict(A_NRC, ANOTHER_NRC);

		assertEquals(A_NRC, conflict.getFirstNrc());
		assertEquals(ANOTHER_NRC, conflict.getSecondNrc());
		assertEquals(40, conflict.getScore());
	}
}
