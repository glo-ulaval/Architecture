package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class ScheduleGeneratorTest {

	private ScheduleGenerator scheduleGenerator;
	private Section sectionMock;
	private Schedule scheduleMock;
	private ConflictDetector conflictDetectorMock;
	private Availability availabilityMock;

	@Before
	public void setUp() {
		scheduleMock = mock(Schedule.class);
		TimeDedicated timeDedicatedMock = mock(TimeDedicated.class);
		when(timeDedicatedMock.getCourseHours()).thenReturn(3);
		sectionMock = mock(Section.class);
		when(sectionMock.getTimeDedicated()).thenReturn(timeDedicatedMock);
		when(sectionMock.getTeachers()).thenReturn(Arrays.asList("A_TEACHER"));
		when(sectionMock.isSupposedToHaveLab()).thenReturn(false);
		conflictDetectorMock = mock(ConflictDetector.class);

		availabilityMock = mock(Availability.class);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(10));
		AvailabilityRepository availabilityRepositoryMock = mock(AvailabilityRepository.class);
		when(availabilityRepositoryMock.findByIdul(anyString())).thenReturn(availabilityMock);

		scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.setAvailabilityRepository(availabilityRepositoryMock);
		scheduleGenerator.setConflictDetector(conflictDetectorMock);
	}

	@Test
	public void canProposeTimeSpotsForSection()
			throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(10));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForCourses(sectionMock, scheduleMock);

		assertEquals(3, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(3)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionWhenNoPropositionPossible()
			throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(true);

		scheduleGenerator.proposeTimeSlotsForSectionForCourses(sectionMock, scheduleMock);
	}

	@Test
	public void shouldTwoProposeTimeSlotsWhenOnlyTwoArePossible()
			throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForCourses(sectionMock, scheduleMock);

		assertEquals(2, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(2)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	private List<TimeSlot> generatePossibleTimeSlots(int numberOfTimeSlots) {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		for (int i = 0; i < numberOfTimeSlots; i++) {
			timeSlots.add(mock(TimeSlot.class));
		}
		return timeSlots;
	}
}
