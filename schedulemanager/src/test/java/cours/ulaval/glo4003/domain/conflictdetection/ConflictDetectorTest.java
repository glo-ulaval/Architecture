package cours.ulaval.glo4003.domain.conflictdetection;

import static org.mockito.Mockito.*;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Schedule;

public class ConflictDetectorTest {

	@Test
	public void canDetectConflicts() {
		ConcomittingCoursesFilter concomittingCoursesFilterMock = mock(ConcomittingCoursesFilter.class);
		CourseLevelFilter courseLevelFilterMock = mock(CourseLevelFilter.class);
		SameTeacherFilter sameTeacherFilterMock = mock(SameTeacherFilter.class);
		UnavailableTeacherFilter unavailableTeacherFilterMock = mock(UnavailableTeacherFilter.class);
		Sink sinkMock = mock(Sink.class);

		Schedule scheduleMock = mock(Schedule.class);
		ConflictDetector conflictDetector = new ConflictDetector();

		conflictDetector.setConcomittingCoursesFilter(concomittingCoursesFilterMock);
		conflictDetector.setCourseLevelFilter(courseLevelFilterMock);
		conflictDetector.setSameTeacherFilter(sameTeacherFilterMock);
		conflictDetector.setUnavailableTeacherFilter(unavailableTeacherFilterMock);
		conflictDetector.setSink(sinkMock);

		conflictDetector.detectConflict(scheduleMock);

		verify(concomittingCoursesFilterMock).connectToPipe(courseLevelFilterMock);
		verify(courseLevelFilterMock).connectToPipe(sameTeacherFilterMock);
		verify(sameTeacherFilterMock).connectToPipe(unavailableTeacherFilterMock);
		verify(unavailableTeacherFilterMock).connectToPipe(sinkMock);
		verify(concomittingCoursesFilterMock).run(scheduleMock);// verify that
																// the pipe is
																// started
																// correctly
	}
}
