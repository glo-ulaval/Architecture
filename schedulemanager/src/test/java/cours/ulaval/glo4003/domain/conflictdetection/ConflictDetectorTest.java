package cours.ulaval.glo4003.domain.conflictdetection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class ConflictDetectorTest {

	private ConcomittingCoursesFilter concomittingCoursesFilterMock;
	private CourseLevelFilter courseLevelFilterMock;
	private SameTeacherFilter sameTeacherFilterMock;
	private UnavailableTeacherFilter unavailableTeacherFilterMock;
	private Sink sinkMock;
	private Schedule scheduleMock;
	private ConflictDetector conflictDetector;

	@Before
	public void setUp() {
		concomittingCoursesFilterMock = mock(ConcomittingCoursesFilter.class);
		courseLevelFilterMock = mock(CourseLevelFilter.class);
		sameTeacherFilterMock = mock(SameTeacherFilter.class);
		unavailableTeacherFilterMock = mock(UnavailableTeacherFilter.class);
		sinkMock = mock(Sink.class);

		scheduleMock = mock(Schedule.class);
		conflictDetector = new ConflictDetector();

		conflictDetector.setConcomittingCoursesFilter(concomittingCoursesFilterMock);
		conflictDetector.setCourseLevelFilter(courseLevelFilterMock);
		conflictDetector.setSameTeacherFilter(sameTeacherFilterMock);
		conflictDetector.setUnavailableTeacherFilter(unavailableTeacherFilterMock);
		conflictDetector.setSink(sinkMock);
	}

	@Test
	public void canDetectConflicts() {
		conflictDetector.detectConflict(scheduleMock);

		verify(concomittingCoursesFilterMock).connectToFilter(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToFilter(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToFilter(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToFilter(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock);
	}

	@Test
	public void canDetectConflictForSection() {
		List<Conflict> emptyConflicts = new ArrayList<Conflict>();
		Section sectionMock = mock(Section.class);
		when(concomittingCoursesFilterMock.run(scheduleMock, sectionMock)).thenReturn(emptyConflicts);

		conflictDetector.detectConflictForSection(scheduleMock, sectionMock);

		verify(concomittingCoursesFilterMock).connectToFilter(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToFilter(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToFilter(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToFilter(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock, sectionMock);
	}

	@Test
	public void canTellASectionWillNotGenerateConflict() {
		List<Conflict> emptyConflicts = new ArrayList<Conflict>();
		Section sectionMock = mock(Section.class);
		when(concomittingCoursesFilterMock.run(scheduleMock, sectionMock)).thenReturn(emptyConflicts);

		boolean result = conflictDetector.willSectionGenerateConflict(scheduleMock, sectionMock);

		verify(concomittingCoursesFilterMock).connectToFilter(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToFilter(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToFilter(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToFilter(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock, sectionMock);
		assertFalse(result);
	}

	@Test
	public void canTellASectionWillGenerateConflict() {
		Conflict conflictMock = mock(Conflict.class);
		List<Conflict> conflicts = Arrays.asList(conflictMock);
		Section sectionMock = mock(Section.class);
		when(concomittingCoursesFilterMock.run(scheduleMock, sectionMock)).thenReturn(conflicts);

		boolean result = conflictDetector.willSectionGenerateConflict(scheduleMock, sectionMock);

		verify(concomittingCoursesFilterMock).connectToFilter(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToFilter(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToFilter(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToFilter(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock, sectionMock);
		assertTrue(result);
	}
}
