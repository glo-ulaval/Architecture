package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.DisponibilityConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameLevelCourseConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;

public class ConflictXMLAdapterTest {

	@Test
	public void canAdaptConflictToConcomittingCourseConflict()
			throws Exception {
		Conflict conflict = new Conflict("nrc1", "nrc2", "", new TimeSlot(), new TimeSlot());
		conflict.setScore(ConcomittingCoursesConflict.SCORE);

		ConflictXMLAdapter adapter = new ConflictXMLAdapter();

		Conflict returnedConflict = adapter.unmarshal(conflict);

		assertTrue(returnedConflict instanceof ConcomittingCoursesConflict);
	}

	@Test
	public void canAdaptConflictToSameLevelCourseConflict()
			throws Exception {
		Conflict conflict = new Conflict("nrc1", "nrc2", "", new TimeSlot(), new TimeSlot());
		conflict.setScore(SameLevelCourseConflict.SCORE);

		ConflictXMLAdapter adapter = new ConflictXMLAdapter();

		Conflict returnedConflict = adapter.unmarshal(conflict);

		assertTrue(returnedConflict instanceof SameLevelCourseConflict);
	}

	@Test
	public void canAdaptConflictToDisponibilityConflict()
			throws Exception {
		Conflict conflict = new Conflict("nrc1", "", "teacher", new TimeSlot(), new TimeSlot());
		conflict.setScore(DisponibilityConflict.SCORE);

		ConflictXMLAdapter adapter = new ConflictXMLAdapter();

		Conflict returnedConflict = adapter.unmarshal(conflict);

		assertTrue(returnedConflict instanceof DisponibilityConflict);
	}

	@Test
	public void canAdaptConflictToUnavailableTeacherConflict()
			throws Exception {
		Conflict conflict = new Conflict("nrc1", "", "teacher", new TimeSlot(), new TimeSlot());
		conflict.setScore(UnavailableTeacherConflict.SCORE);

		ConflictXMLAdapter adapter = new ConflictXMLAdapter();

		Conflict returnedConflict = adapter.unmarshal(conflict);

		assertTrue(returnedConflict instanceof UnavailableTeacherConflict);
	}

	@Test
	public void canAdaptConflictToSameTeacherConflict()
			throws Exception {
		Conflict conflict = new Conflict("nrc1", "", "teacher", new TimeSlot(), new TimeSlot());
		conflict.setScore(SameTeacherConflict.SCORE);

		ConflictXMLAdapter adapter = new ConflictXMLAdapter();

		Conflict returnedConflict = adapter.unmarshal(conflict);

		assertTrue(returnedConflict instanceof SameTeacherConflict);
	}
}
