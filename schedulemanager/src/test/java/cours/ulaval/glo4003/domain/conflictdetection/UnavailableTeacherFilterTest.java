package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.AvailabilityLevel;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class UnavailableTeacherFilterTest {

	@Test
	public void canInstantiateUnavailableTeacherFilter() {
		UnavailableTeacherFilter filter = new UnavailableTeacherFilter();

		assertNotNull(filter);
	}

	@Test
	public void canDetectTeacherAvailabilityConflict() {
		TimeSlot timeSlotMock = mock(TimeSlot.class);
		List<TimeSlot> timeSlotsMocks = Arrays.asList(timeSlotMock);

		List<String> teachers = Arrays.asList("unavailable_teacher");
		List<String> otherteachers = Arrays.asList("available_teacher");

		Section aSectionMock = mock(Section.class);
		when(aSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.getTeachers()).thenReturn(teachers);
		Section anotherSectionMock = mock(Section.class);
		when(anotherSectionMock.getCoursesAndLabTimeSlots()).thenReturn(timeSlotsMocks);
		when(aSectionMock.getTeachers()).thenReturn(otherteachers);
		List<Section> sectionsMocks = Arrays.asList(aSectionMock, anotherSectionMock);

		Availability availabilityMock = mock(Availability.class);
		when(availabilityMock.isAvailableForTimeSlot(any(TimeSlot.class))).thenReturn(AvailabilityLevel.Unavailable);
		Availability otherAvailabilityMock = mock(Availability.class);
		when(otherAvailabilityMock.isAvailableForTimeSlot(any(TimeSlot.class))).thenReturn(AvailabilityLevel.Available);

		AvailabilityRepository repositoryMock = mock(AvailabilityRepository.class);
		when(repositoryMock.findByIdul("unavailable_teacher")).thenReturn(availabilityMock);
		when(repositoryMock.findByIdul("available_teacher")).thenReturn(otherAvailabilityMock);

		Schedule scheduleMock = mock(Schedule.class);
		when(scheduleMock.getSectionsList()).thenReturn(sectionsMocks);

		UnavailableTeacherFilter filter = new UnavailableTeacherFilter();
		filter.setRepository(repositoryMock);

		filter.run(scheduleMock);

		verify(scheduleMock, atLeast(1)).addAll(anyListOf(Conflict.class));
	}
}
