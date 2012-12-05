package cours.ulaval.glo4003.aspect;

import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import cours.ulaval.glo4003.domain.Notification;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;
import cours.ulaval.glo4003.domain.repository.UserRepository;

@Aspect
public class SendEmailOnModifyAspect {

	@Inject
	ScheduleRepository scheduleRepository;

	@Inject
	JavaMailSenderImpl mailSender;

	@Inject
	UserRepository userRepository;

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.updateSection(..))")
	private void sendEmailOnModifySection(JoinPoint pjp) throws Exception {
		String id = pjp.getArgs()[0].toString();
		String nrc = pjp.getArgs()[1].toString();

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		// Normalement on partirais un thread pour éviter à l'usager le long
		// traitement
		sendEmailToAll(section.getTeachers());
	}

	@After("execution(* cours.ulaval.glo4003.controller.ScheduleController.postEditSectionAndReturnToLastView(..))")
	private void addNotificationOnUpdate(JoinPoint pjp) throws Exception {
		String id = pjp.getArgs()[0].toString();
		String nrc = pjp.getArgs()[3].toString();

		Schedule schedule = scheduleRepository.findById(id);
		Section section = schedule.getSections().get(nrc);

		// Normalement on partirais un thread pour éviter à l'usager le long
		// traitement
		sendEmailToAll(section.getTeachers());
	}

	private void sendEmailToAll(List<String> teachers) {
		for (String teacherIdul : teachers) {
			User user = userRepository.findByIdul(teacherIdul);
			if (user.hasValidEmailAdress()) {
				sendEmailTo(user.getEmailAddress());
			}
		}
	}

	private void sendEmailTo(String emailAddress) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("noreply@gmail.com");
		msg.setTo(emailAddress);
		msg.setSubject("Schedule Manager - Notification");
		msg.setText(Notification.SECTION_MODIFIED);
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
