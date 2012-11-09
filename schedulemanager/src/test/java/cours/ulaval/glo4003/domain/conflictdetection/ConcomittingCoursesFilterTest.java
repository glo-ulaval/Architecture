package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;

public class ConcomittingCoursesFilterTest {

	@Test
	public void canInstantiateAConcommitingCoursesFilter() {
		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();

		assertNotNull(filter);
	}

	@Test
	public void canDetectConcimittingCoursesConflicts() {
		Prerequisite prerequisiteMock = mock(Prerequisite.class);
		when(prerequisiteMock.containsAcronym("GLO-3000")).thenReturn(true);
		when(prerequisiteMock.getIsConcomitant()).thenReturn(true);
		Prerequisite aPrerequisiteMock = mock(Prerequisite.class);
		when(aPrerequisiteMock.containsAcronym(anyString())).thenReturn(false);

		Course aCourseMock = mock(Course.class);
		Course anotherCourseMock = mock(Course.class);
		when(aCourseMock.isConcomitting(anotherCourseMock)).thenReturn(true);
		when(anotherCourseMock.isConcomitting(aCourseMock)).thenReturn(false);

		XMLCourseRepository courseRepositoryMock = mock(XMLCourseRepository.class);
		when(courseRepositoryMock.findByAcronym("GLO-4000")).thenReturn(aCourseMock);
		when(courseRepositoryMock.findByAcronym("GLO-3000")).thenReturn(anotherCourseMock);

		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		Section aSectionMock = mock(Section.class);
		when(aSectionMock.getCourseAcronym()).thenReturn("GLO-4000");
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		Section anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-3000");
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		Schedule scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();
		filter.setRepository(courseRepositoryMock);

		filter.run(scheduleMock);

		verify(scheduleMock).addAll(anyListOf(Conflict.class));
	}
}
