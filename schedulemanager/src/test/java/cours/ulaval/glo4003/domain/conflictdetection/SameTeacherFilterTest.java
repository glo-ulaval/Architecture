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

	private Schedule schedule;
	private SameTeacherFilter filter;
	private List<Section> sections;
	private Filter nextFilterMock;
	private Section sectionMock;
	private Section anotherSectionMock;

	@Before
	public void setUp()
			throws Exception {
		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		sectionMock = mock(Section.class);
		when(sectionMock.getNrc()).thenReturn("AN_NRC");
		when(sectionMock.getTeachers()).thenReturn(Arrays.asList(TEACHER));
		when(sectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getNrc()).thenReturn("AN_NRC2");
		when(anotherSectionMock.hasTeacher(TEACHER)).thenReturn(true);
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		sections = Arrays.asList(sectionMock, anotherSectionMock);

		schedule = mock(Schedule.class);
		when(schedule.getSectionsList()).thenReturn(sections);

		nextFilterMock = mock(Filter.class);

		filter = new SameTeacherFilter();
		filter.connectToFilter(nextFilterMock);
	}

	@Test
	public void canVerifyIsThereIsAConflictForTwoSectionForATeacher() {
		List<Conflict> conflicts = filter.run(schedule);

		assertEquals(1, conflicts.size());
		verify(anotherSectionMock).hasTeacher(anyString());
	}
}
