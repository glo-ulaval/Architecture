package cours.ulaval.glo4003.aspect;

import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import cours.ulaval.glo4003.domain.Notification;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Aspect
public class AddNotificationAspect {

	@Inject
	ScheduleRepository scheduleRepository;

	@Inject
	UserRepository userRepository;

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.updateSection(..))")
	private void onUpdateSection(JoinPoint pjp) throws Exception {
		String id = pjp.getArgs()[0].toString();
		String nrc = pjp.getArgs()[1].toString();
		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		addNotification(id, nrc, schedule.getYear(), schedule.getSemester().toString(), section.getTeachers());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.postEditSection(..))")
	private void onPostEditSection(JoinPoint pjp) throws Exception {
		String id = pjp.getArgs()[0].toString();
		String nrc = pjp.getArgs()[3].toString();
		String year = pjp.getArgs()[1].toString();
		Semester semester = (Semester) pjp.getArgs()[2];

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		addNotification(id, nrc, year, semester.toString(), section.getTeachers());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.postEditSectionAndReturnToLastView(..))")
	private void onEditSectionAndRedirect(JoinPoint pjp) throws Exception {
		String id = pjp.getArgs()[0].toString();
		String nrc = pjp.getArgs()[3].toString();
		String year = pjp.getArgs()[1].toString();
		Semester semester = (Semester) pjp.getArgs()[2];

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		addNotification(id, nrc, year, semester.toString(), section.getTeachers());
	}

	private void addNotification(String id, String nrc, String year, String semester, List<String> teachers) throws Exception {
		for (String teacherIdul : teachers) {
			String path = "/schedule/editsection/" + id + "/" + year + "/" + semester + "/" + nrc + "/calendar";
			User user = userRepository.findByIdul(teacherIdul);
			user.addNotification(new Notification(Notification.SECTION_MODIFIED, path));

			userRepository.store(user);
		}
	}
}
