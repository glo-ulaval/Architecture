package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class SameTeacherFilterTest {

	private static final String TEACHER = "teacher1";

	private Schedule scheduleMock;
	private SameTeacherFilter filter;
	private List<Section> sections;
	private Filter nextFilterMock;
	private Section aSectionMock;
	private Section anotherSectionMock;

	@Before
	public void setUp()
			throws Exception {
		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		aSectionMock = mock(Section.class);
		when(aSectionMock.getNrc()).thenReturn("AN_NRC");
		when(aSectionMock.getTeachers()).thenReturn(Arrays.asList(TEACHER));
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getNrc()).thenReturn("AN_NRC2");
		when(anotherSectionMock.hasTeacher(TEACHER)).thenReturn(true);
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		sections = Arrays.asList(aSectionMock, anotherSectionMock);

		scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sections);

		nextFilterMock = mock(Filter.class);

		filter = new SameTeacherFilter();
		filter.connectToFilter(nextFilterMock);
	}

	@Test
	public void canVerifyIsThereIsAConflictForTwoSectionForATeacher() {
		List<Conflict> conflicts = filter.run(scheduleMock);

		assertEquals(1, conflicts.size());
		verify(anotherSectionMock).hasTeacher(anyString());
		verify(nextFilterMock).run(scheduleMock);
	}

	@Test
	public void canSayIfSectionWouldCauseSameLevelCourseConflictWithSchedule() {
		List<Conflict> conflicts = filter.run(scheduleMock, aSectionMock);

		assertEquals(1, conflicts.size());
		verify(aSectionMock).hasTeacher(TEACHER);
		verify(nextFilterMock).run(scheduleMock, aSectionMock);
	}

}
