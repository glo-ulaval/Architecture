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

public class CourseLevelFilterTest {

	private Schedule scheduleMock;
	private Section aSectionMock;
	private Section anotherSectionMock;
	private Filter nextFilterMock;
	private CourseLevelFilter filter;

	@Before
	public void setUp() {

		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		aSectionMock = mock(Section.class);
		anotherSectionMock = mock(Section.class);
		when(aSectionMock.getCourseAcronym()).thenReturn("IFT-1000");
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.areSameLevel(anotherSectionMock)).thenReturn(true);
		when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-1000");
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(anotherSectionMock.areSameLevel(aSectionMock)).thenReturn(true);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		nextFilterMock = mock(Filter.class);
		filter = new CourseLevelFilter();
		filter.connectToFilter(nextFilterMock);
	}

	@Test
	public void canInstantiateACourseLevelFilter() {
		CourseLevelFilter filter = new CourseLevelFilter();

		assertNotNull(filter);
	}

	@Test
	public void canDetectSameLevelCourseConflict() {
		List<Conflict> conflicts = filter.run(scheduleMock);

		assertEquals(1, conflicts.size());
		verify(aSectionMock).areSameLevel(anotherSectionMock);
		verify(nextFilterMock).run(scheduleMock);
	}

	@Test
	public void canSayIfSectionWouldCauseSameLevelCourseConflictWithSchedule() {
		List<Conflict> conflicts = filter.run(scheduleMock, aSectionMock);

		assertEquals(1, conflicts.size());
		verify(aSectionMock).areSameLevel(anotherSectionMock);
		verify(nextFilterMock).run(scheduleMock, aSectionMock);
	}

}
