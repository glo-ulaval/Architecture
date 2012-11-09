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
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
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
		List<Prerequisite> prerequisitesMocks = Arrays.asList(prerequisiteMock);
		Prerequisite aPrerequisiteMock = mock(Prerequisite.class);
		when(aPrerequisiteMock.containsAcronym(anyString())).thenReturn(false);
		List<Prerequisite> aPrerequisitesMocks = Arrays.asList(aPrerequisiteMock);

		Course aCourseMock = mock(Course.class);
		when(aCourseMock.getPrerequisites()).thenReturn(prerequisitesMocks);
		Course anotherCourseMock = mock(Course.class);
		when(anotherCourseMock.getPrerequisites()).thenReturn(aPrerequisitesMocks);

		XMLCourseRepository courseRepositoryMock = mock(XMLCourseRepository.class);
		when(courseRepositoryMock.findByAcronym("GLO-4000")).thenReturn(aCourseMock);
		when(courseRepositoryMock.findByAcronym("GLO-3000")).thenReturn(anotherCourseMock);

		TimeSlot timeSlotMock = mock(TimeSlot.class);
		when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		Section aSectionMock = mock(Section.class);
		when(aSectionMock.getCourseAcronym()).thenReturn("GLO-4000");
		when(aSectionMock.getCourseTimeSlots()).thenReturn(timeSlotsMocks);
		Section anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-3000");
		when(anotherSectionMock.getCourseTimeSlots()).thenReturn(timeSlotsMocks);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		Schedule scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		ConcomittingCoursesFilter filter = new ConcomittingCoursesFilter();
		filter.setRepo(courseRepositoryMock);

		filter.run(scheduleMock);

		verify(scheduleMock).add(any(ConcomittingCoursesConflict.class));
	}

	// private Schedule prepareMockedSchedule() {
	// Prerequisite prerequisiteMock = mock(Prerequisite.class);
	// when(prerequisiteMock.containsAcronym("GLO-3000")).thenReturn(true);
	// when(prerequisiteMock.getIsConcomitant()).thenReturn(true);
	// List<Prerequisite> prerequisitesMocks = Arrays.asList(prerequisiteMock);
	// Prerequisite aPrerequisiteMock = mock(Prerequisite.class);
	// when(aPrerequisiteMock.containsAcronym(anyString())).thenReturn(false);
	// List<Prerequisite> aPrerequisitesMocks =
	// Arrays.asList(aPrerequisiteMock);
	//
	// Course aCourseMock = mock(Course.class);
	// when(aCourseMock.getPrerequisites()).thenReturn(prerequisitesMocks);
	// Course anotherCourseMock = mock(Course.class);
	// when(anotherCourseMock.getPrerequisites()).thenReturn(aPrerequisitesMocks);
	//
	// XMLCourseRepository courseRepositoryMock =
	// mock(XMLCourseRepository.class);
	// when(courseRepositoryMock.findByAcronym("GLO-4000")).thenReturn(aCourseMock);
	// when(courseRepositoryMock.findByAcronym("GLO-3000")).thenReturn(anotherCourseMock);
	//
	// TimeSlot timeSlotMock = mock(TimeSlot.class);
	// when(timeSlotMock.isOverlapping(any(TimeSlot.class))).thenReturn(true);
	// List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);
	//
	// Section aSectionMock = mock(Section.class);
	// when(aSectionMock.getCourseAcronym()).thenReturn("GLO-4000");
	// when(aSectionMock.getCourseTimeSlots()).thenReturn(timeSlotsMocks);
	// Section anotherSectionMock = mock(Section.class);
	// when(anotherSectionMock.getCourseAcronym()).thenReturn("GLO-3000");
	// when(anotherSectionMock.getCourseTimeSlots()).thenReturn(timeSlotsMocks);
	// List<Section> sectionsMocks = Arrays.asList(aSectionMock,
	// anotherSectionMock);
	//
	// Schedule schedule = mock(Schedule.class);
	// when(schedule.getSectionsList()).thenReturn(sectionsMocks);
	//
	// return schedule;
	// }
}
