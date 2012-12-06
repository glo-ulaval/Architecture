package cours.ulaval.glo4003.aspect;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;

@Aspect
public class DetectConflictAspect {

	@Inject
	ScheduleRepository scheduleRepository;

	@Inject
	private ConflictDetector conflictDetector;

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.deleteSection(..))")
	private void onDeleteSection(JoinPoint pjp) throws Exception {
		detectConflictFor(pjp.getArgs()[0].toString());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.addSection(..))")
	private void onAddSection(JoinPoint pjp) throws Exception {
		detectConflictFor(pjp.getArgs()[0].toString());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.postEditSectionAndReturnToLastView(..))")
	private void onPostEditSectionAndRedirect(JoinPoint pjp) throws Exception {
		detectConflictFor(pjp.getArgs()[0].toString());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.reuseSelectedSchedule(..))")
	private void onReuseSelectedSchedule(JoinPoint pjp) throws Exception {
		detectConflictFor(pjp.getArgs()[0].toString());
	}

	private void detectConflictFor(String scheduleId) throws Exception {
		Schedule schedule = scheduleRepository.findById(scheduleId);
		schedule.clearConflicts();
		conflictDetector.detectConflict(schedule);
		scheduleRepository.store(schedule);
	}

	// WARNING : FOR TEST PURPOSE ONLY
	public void setConflictDetector(ConflictDetector conflictDetector) {
		this.conflictDetector = conflictDetector;
	}
}
