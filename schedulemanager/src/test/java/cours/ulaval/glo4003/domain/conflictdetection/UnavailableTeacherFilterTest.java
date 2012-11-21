package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.AvailabilityLevel;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class UnavailableTeacherFilterTest {

	private Filter nextFilterMock;
	private Availability availabilityMock;
	private Availability otherAvailabilityMock;
	private Schedule scheduleMock;
	private UnavailableTeacherFilter filter;
	private TimeSlot timeSlotMock;
	private Section aSectionMock;
	private Section anotherSectionMock;

	@Before
	public void setUp() {
		timeSlotMock = mock(TimeSlot.class);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		List<String> teachers = Arrays.asList("unavailable_teacher", "available_teacher");

		aSectionMock = mock(Section.class);
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.getTeachers()).thenReturn(teachers);
		anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.getTeachers()).thenReturn(teachers);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		availabilityMock = mock(Availability.class);
		when(availabilityMock.isAvailableForTimeSlot(any(TimeSlot.class))).thenReturn(AvailabilityLevel.Unavailable);
		otherAvailabilityMock = mock(Availability.class);
		when(otherAvailabilityMock.isAvailableForTimeSlot(any(TimeSlot.class))).thenReturn(AvailabilityLevel.Available);

		AvailabilityRepository repositoryMock = mock(AvailabilityRepository.class);
		when(repositoryMock.findByIdul("unavailable_teacher")).thenReturn(availabilityMock);
		when(repositoryMock.findByIdul("available_teacher")).thenReturn(otherAvailabilityMock);

		scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		nextFilterMock = mock(Filter.class);

		filter = new UnavailableTeacherFilter();
		filter.connectToFilter(nextFilterMock);
		filter.setRepository(repositoryMock);
	}

	@Test
	public void canDetectTeacherAvailabilityConflict() {
		List<Conflict> conflicts = filter.run(scheduleMock);

		assertEquals(1, conflicts.size());
		verify(availabilityMock, atLeast(1)).isAvailableForTimeSlot(timeSlotMock);
		verify(otherAvailabilityMock, atLeast(1)).isAvailableForTimeSlot(timeSlotMock);
	}

	@Test
	public void canSayIfSectionWouldCauseSameLevelCourseConflictWithSchedule() {
		List<Conflict> conflicts = filter.run(scheduleMock, aSectionMock);

		assertEquals(1, conflicts.size());
		verify(availabilityMock, atLeast(1)).isAvailableForTimeSlot(timeSlotMock);
		verify(otherAvailabilityMock, atLeast(1)).isAvailableForTimeSlot(timeSlotMock);
	}

}
