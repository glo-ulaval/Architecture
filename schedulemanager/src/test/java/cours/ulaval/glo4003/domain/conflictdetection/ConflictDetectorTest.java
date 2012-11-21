package cours.ulaval.glo4003.domain.conflictdetection;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;

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
		Section sectionMock = mock(Section.class);

		conflictDetector.detectConflictForSection(scheduleMock, sectionMock);

		verify(concomittingCoursesFilterMock).connectToFilter(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToFilter(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToFilter(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToFilter(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock, sectionMock);
	}
}
