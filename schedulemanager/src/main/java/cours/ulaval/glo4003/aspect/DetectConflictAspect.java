package cours.ulaval.glo4003.aspect;

import javax.inject.Inject;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;

@Aspect
public class DetectConflictAspect {

	@Inject
	private ConflictDetector conflictDetector;

	@Before("execution(* cours.ulaval.glo4003.domain.repository.ScheduleRepository.store(..)) && args(schedule)")
	private void onStoreSchedule(Schedule schedule) {
		schedule.clearConflicts();
		conflictDetector.detectConflict(schedule);
	}

	// WARNING : FOR TEST PURPOSE ONLY
	public void setConflictDetector(ConflictDetector conflictDetector) {
		this.conflictDetector = conflictDetector;
	}
}
