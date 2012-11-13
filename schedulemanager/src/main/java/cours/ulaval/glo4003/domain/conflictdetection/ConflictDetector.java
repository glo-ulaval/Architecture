package cours.ulaval.glo4003.domain.conflictdetection;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Schedule;

public class ConflictDetector extends Pipe {
	@Inject
	private ConcomittingCoursesFilter concomittingCoursesFilter;
	@Inject
	private CourseLevelFilter courseLevelFilter;
	@Inject
	private SameTeacherFilter sameTeacherFilter;
	@Inject
	private UnavailableTeacherFilter unavailableTeacherFilter;
	private Sink sink = new Sink();

	public void detectConflict(Schedule schedule) {
		run(schedule);
	}

	@Override
	protected void run(Schedule schedule) {
		connectFilters();
		startPipe(schedule);
	}

	private void connectFilters() {
		this.connectToPipe(concomittingCoursesFilter);
		concomittingCoursesFilter.connectToPipe(courseLevelFilter);
		courseLevelFilter.connectToPipe(sameTeacherFilter);
		sameTeacherFilter.connectToPipe(unavailableTeacherFilter);
		unavailableTeacherFilter.connectToPipe(sink);
	}

	private void startPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}

	// WARNING, DO NOT USE - the following methods are for tests only
	public void setConcomittingCoursesFilter(ConcomittingCoursesFilter concomittingCoursesFilter) {
		this.concomittingCoursesFilter = concomittingCoursesFilter;
	}

	public void setCourseLevelFilter(CourseLevelFilter courseLevelFilter) {
		this.courseLevelFilter = courseLevelFilter;
	}

	public void setSameTeacherFilter(SameTeacherFilter sameTeacherFilter) {
		this.sameTeacherFilter = sameTeacherFilter;
	}

	public void setUnavailableTeacherFilter(UnavailableTeacherFilter unavailableTeacherFilter) {
		this.unavailableTeacherFilter = unavailableTeacherFilter;
	}

	public void setSink(Sink sink) {
		this.sink = sink;
	}

}
