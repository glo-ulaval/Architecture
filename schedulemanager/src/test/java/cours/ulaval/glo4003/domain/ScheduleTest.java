package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class ScheduleTest {

	private static final Integer A_SCORE = 44;
	private static String AN_ID = "anId";
	private static String AN_NRC = "anNRC";

	private Schedule schedule;
	private Section section;
	private ConcomittingCoursesConflict conflict;

	@Before
	public void setUp() {
		section = mock(Section.class);
		when(section.getNrc()).thenReturn(AN_NRC);

		conflict = mock(ConcomittingCoursesConflict.class);
		when(conflict.getScore()).thenReturn(A_SCORE);

		schedule = new Schedule(AN_ID);
	}

	@Test
	public void canAddASection() {
		schedule.add(section);

		assertEquals(section, schedule.getSections().get(AN_NRC));
	}

	@Test
	public void cannotAddTheSameSection() {
		schedule.add(section);

		schedule.add(section);

		assertEquals(1, schedule.getSections().size());
	}

	@Test
	public void canDeleteASection() {
		schedule.add(section);

		schedule.delete(section.getNrc());

		assertEquals(0, schedule.getSections().size());
	}

	@Test
	public void canAddAConflict() {
		schedule.add(conflict);

		assertEquals(1, schedule.getConflicts().size());
	}

	@Test
	public void canCalculateScore() {
		schedule.add(conflict);

		schedule.calculateScore();

		assertEquals(A_SCORE, schedule.getScore());
	}

	@Test
	public void canAddAllConflicts() {
		schedule.addAll(Arrays.asList((Conflict) conflict, (Conflict) conflict));

		assertEquals(2, schedule.getConflicts().size());
	}
}
