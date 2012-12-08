package cours.ulaval.glo4003.aspect;

import java.util.List;

import javax.inject.Inject;

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

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.updateSection(..)) && args(id, nrc, ..)")
	private void onUpdateSection(String id, String nrc) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		addNotification(id, nrc, schedule.getYear(), schedule.getSemester().toString(), section.getTeachers());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.postEditSectionAndReturnToLastView(..)) && args(id, year, semester, sectionNrc, ..)")
	private void onEditSectionAndRedirect(String id, String year, Semester semester, String sectionNrc) throws Exception {
		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(sectionNrc);

		addNotification(id, sectionNrc, year, semester.toString(), section.getTeachers());
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
