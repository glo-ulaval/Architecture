package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class ConcomittingCoursesFilterTest {

	@Test
	public void canInstantiateAConcommitingCoursesFilter() {
		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();

		assertNotNull(filter);
	}

	@Test
	public void canDetectConcimittingCoursesConflicts() {
		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		Section aSectionMock = mock(Section.class);
		Section anotherSectionMock = mock(Section.class);
		when(aSectionMock.getCourseAcronym()).thenReturn("GLO-4000");
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.areConcomitting(anotherSectionMock)).thenReturn(true);
		when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-3000");
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		Schedule scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();

		filter.run(scheduleMock);

		verify(scheduleMock).addAll(anyListOf(Conflict.class));
		verify(aSectionMock).areConcomitting(anotherSectionMock);
	}
}
