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
import cours.ulaval.glo4003.domain.exception.FailedScheduleGenerationException;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class ScheduleGeneratorTest {

	private ScheduleGenerator scheduleGenerator;
	private Section sectionMock;
	private Schedule scheduleMock;
	private Section sectionWithLabMock;
	private ConflictDetector conflictDetectorMock;
	private Availability availabilityMock;

	@Before
	public void setUp() {
		scheduleMock = mock(Schedule.class);
		TimeDedicated timeDedicatedMock = mock(TimeDedicated.class);
		when(timeDedicatedMock.getCourseHours()).thenReturn(3);
		TimeDedicated timeDedicatedWithLabHoursMock = mock(TimeDedicated.class);
		when(timeDedicatedWithLabHoursMock.getCourseHours()).thenReturn(3);
		when(timeDedicatedWithLabHoursMock.getLabHours()).thenReturn(2);

		sectionMock = mock(Section.class);
		when(sectionMock.getTimeDedicated()).thenReturn(timeDedicatedMock);
		when(sectionMock.getTeachers()).thenReturn(Arrays.asList("A_TEACHER"));
		when(sectionMock.isSupposedToHaveLab()).thenReturn(false);
		sectionWithLabMock = mock(Section.class);
		when(sectionWithLabMock.getTimeDedicated()).thenReturn(timeDedicatedWithLabHoursMock);
		when(sectionWithLabMock.getTeachers()).thenReturn(Arrays.asList("A_TEACHER"));
		when(sectionWithLabMock.isSupposedToHaveLab()).thenReturn(true);

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
	public void canProposeTimeSpotsForSectionForCourse() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(10));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForCourses(sectionMock, scheduleMock);

		assertEquals(3, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(3)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionWhenNoPropositionPossibleForCourse() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(true);

		scheduleGenerator.proposeTimeSlotsForSectionForCourses(sectionMock, scheduleMock);
	}

	@Test
	public void shouldTwoProposeTimeSlotsWhenOnlyTwoArePossibleForCourse() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForLab(sectionMock, scheduleMock);

		assertEquals(2, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(2)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	@Test
	public void canProposeTimeSpotsForSectionForLab() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(10));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForLab(sectionMock, scheduleMock);

		assertEquals(3, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(3)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionWhenNoPropositionPossibleForLab() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(true);

		scheduleGenerator.proposeTimeSlotsForSectionForLab(sectionMock, scheduleMock);
	}

	@Test
	public void shouldTwoProposeTimeSlotsWhenOnlyTwoArePossibleForLab() throws Exception {
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForLab(sectionMock, scheduleMock);

		assertEquals(2, possibleTimeSlots.size());
		verify(conflictDetectorMock, atLeast(2)).willSectionGenerateConflict(scheduleMock, sectionMock);
	}

	@Test
	public void canProposeCompleteScheduleForSpecifiedSectionList() throws Exception {
		List<Section> mockedSections = prepareSections();
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionMock)).thenReturn(false);
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionWithLabMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(3));
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		Schedule generatedSchedule = scheduleGenerator.generateSchedule(mockedSections);

		assertNotNull(generatedSchedule);
		assertFalse(generatedSchedule.getSectionsList().isEmpty());
		verify(conflictDetectorMock, atLeast(2)).willSectionGenerateConflict(any(Schedule.class), any(Section.class));
		verify(availabilityMock, times(2)).generatePossibleTimeSlotsForCourse(anyInt());
		verify(availabilityMock).generatePossibleTimeSlotsForLab(anyInt());
	}

	@Test(expected = FailedScheduleGenerationException.class)
	public void shouldThrowExceptionWhenASectionCourseBePlacedInSchedule() throws Exception {
		List<Section> mockedSections = prepareSections();
		when(conflictDetectorMock.willSectionGenerateConflict(any(Schedule.class), any(Section.class))).thenReturn(true);
		when(conflictDetectorMock.willSectionGenerateConflict(scheduleMock, sectionWithLabMock)).thenReturn(false);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(3));
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		scheduleGenerator.generateSchedule(mockedSections);
	}

	@Test(expected = FailedScheduleGenerationException.class)
	public void shouldThrowExceptionWhenASectionLabBePlacedInSchedule() throws Exception {
		List<Section> mockedSections = prepareSections();
		when(conflictDetectorMock.willSectionGenerateConflict(any(Schedule.class), any(Section.class))).thenReturn(false);
		when(conflictDetectorMock.willSectionGenerateConflict(any(Schedule.class), any(Section.class))).thenReturn(true);
		when(availabilityMock.generatePossibleTimeSlotsForCourse(anyInt())).thenReturn(generatePossibleTimeSlots(3));
		when(availabilityMock.generatePossibleTimeSlotsForLab(anyInt())).thenReturn(generatePossibleTimeSlots(2));

		scheduleGenerator.generateSchedule(mockedSections);
	}

	private List<TimeSlot> generatePossibleTimeSlots(int numberOfTimeSlots) {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		for (int i = 0; i < numberOfTimeSlots; i++) {
			timeSlots.add(mock(TimeSlot.class));
		}
		return timeSlots;
	}

	private List<Section> prepareSections() {
		List<Section> sections = new ArrayList<Section>();
		sections.add(sectionMock);
		sections.add(sectionWithLabMock);
		return sections;
	}
}
