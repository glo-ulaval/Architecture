package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;

public class ScheduleTest {

	private static final Integer A_SCORE = 44;
	private static final String A_YEAR = "2002";
	private static final String A_COURSE_ACRONYM = "GLO-2002";
	private static final String A_OLD_COURSE_ACRONYM = "IFT-1000";
	private static final String AN_ID = "anId";
	private static final String AN_NRC = "anNRC";
	private static final String ANOTHER_NRC = "anotherNrc";

	private Schedule schedule;
	private Section sectionMock;
	private Conflict conflictMock;

	@Before
	public void setUp() {
		sectionMock = mock(Section.class);
		when(sectionMock.getNrc()).thenReturn(AN_NRC);

		conflictMock = mock(Conflict.class);
		when(conflictMock.getScore()).thenReturn(A_SCORE);

		schedule = new Schedule(AN_ID);
		schedule.setYear(A_YEAR);
	}

	@Test
	public void canAddASection() {
		schedule.add(sectionMock);

		assertEquals(sectionMock, schedule.getSections().get(AN_NRC));
	}

	@Test
	public void cannotAddTheSameSection() {
		schedule.add(sectionMock);

		schedule.add(sectionMock);

		assertEquals(1, schedule.getSections().size());
	}

	@Test
	public void canDeleteASection() {
		schedule.add(sectionMock);

		schedule.delete(sectionMock.getNrc());

		assertEquals(0, schedule.getSections().size());
	}

	@Test
	public void canAddAConflict() {
		schedule.add(conflictMock);

		assertEquals(1, schedule.getConflicts().size());
	}

	@Test
	public void canAddStatus() {
		schedule.addStatus("THEUD", ScheduleStatus.Accepted);

		assertEquals(ScheduleStatus.Accepted, schedule.getStatus("THEUD"));
	}

	@Test
	public void canCalculateScore() {
		schedule.add(conflictMock);

		schedule.calculateScore();

		assertEquals(A_SCORE, schedule.getScore());
	}

	@Test
	public void canAddAllConflicts() {
		schedule.addAll(Arrays.asList(conflictMock, conflictMock));

		assertEquals(2, schedule.getConflicts().size());
	}

	@Test
	public void canClearConflictsList() {
		schedule.addAll(Arrays.asList(conflictMock, conflictMock));

		schedule.clearConflicts();

		assertEquals(0, schedule.getConflicts().size());
	}

	@Test
	public void canCopySectionsFromScheduleForCurrentYearOffering() {
		Offering offeringMock = mock(Offering.class);
		when(offeringMock.hasCourse(A_COURSE_ACRONYM)).thenReturn(true);
		when(offeringMock.hasCourse(A_OLD_COURSE_ACRONYM)).thenReturn(false);

		OfferingRepository repositoryMock = mock(OfferingRepository.class);
		when(repositoryMock.find(A_YEAR)).thenReturn(offeringMock);

		Section oldSectionMock = mock(Section.class);
		when(oldSectionMock.getCourseAcronym()).thenReturn(A_OLD_COURSE_ACRONYM);
		when(oldSectionMock.getNrc()).thenReturn(ANOTHER_NRC);
		when(sectionMock.getCourseAcronym()).thenReturn(A_COURSE_ACRONYM);
		when(sectionMock.clone()).thenReturn(sectionMock);

		schedule.add(sectionMock);
		schedule.add(oldSectionMock);

		Schedule newSchedule = new Schedule();
		newSchedule.setOfferingRepository(repositoryMock);

		newSchedule.copySectionsFromOtherSchedule(schedule);

		verify(repositoryMock).find(A_YEAR);
		verify(offeringMock).hasCourse(A_COURSE_ACRONYM);
		verify(offeringMock).hasCourse(A_OLD_COURSE_ACRONYM);
		verify(sectionMock).getCourseAcronym();
		verify(oldSectionMock).getCourseAcronym();
		assertEquals(1, newSchedule.getSectionsList().size());
	}

	@Test
	public void canGetCorrectConcernedUsers() {
		List<String> teachers = new ArrayList<String>();
		teachers.add("THEUD");
		teachers.add("NATAW");

		when(sectionMock.getTeachers()).thenReturn(teachers);
		schedule.add(sectionMock);

		assertEquals(teachers.size(), schedule.getConcernedUsers().size());
	}
}
