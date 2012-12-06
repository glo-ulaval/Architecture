package cours.ulaval.glo4003.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cours.ulaval.glo4003.aspect.AddNotificationAspect;
import cours.ulaval.glo4003.aspect.DetectConflictAspect;
import cours.ulaval.glo4003.aspect.SendEmailOnModifyAspect;
import cours.ulaval.glo4003.controller.security.UserSecurityService;
import cours.ulaval.glo4003.domain.ScheduleGenerator;
import cours.ulaval.glo4003.domain.conflictdetection.ConcomittingCoursesFilter;
import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.conflictdetection.CourseLevelFilter;
import cours.ulaval.glo4003.domain.conflictdetection.SameTeacherFilter;
import cours.ulaval.glo4003.domain.conflictdetection.UnavailableTeacherFilter;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;
import cours.ulaval.glo4003.domain.repository.UserRepository;
import cours.ulaval.glo4003.persistence.XMLAvailabilityRepository;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;
import cours.ulaval.glo4003.persistence.XMLOfferingRepository;
import cours.ulaval.glo4003.persistence.XMLProgramSheetRepository;
import cours.ulaval.glo4003.persistence.XMLScheduleRepository;
import cours.ulaval.glo4003.persistence.XMLUserRepository;
import cours.ulaval.glo4003.utils.ConfigManager;
import cours.ulaval.glo4003.utils.ResourcesLoader;

@Configuration
public class AppConfig {

	@Bean
	public CourseRepository courseRepository()
			throws Exception {
		return new XMLCourseRepository();
	}

	@Bean
	public OfferingRepository offeringRepository()
			throws Exception {
		return new XMLOfferingRepository();
	}

	@Bean
	public ScheduleRepository scheduleRepository()
			throws Exception {
		return new XMLScheduleRepository();
	}

	@Bean
	public UserRepository userRepository()
			throws Exception {
		return new XMLUserRepository();
	}

	@Bean
	public AvailabilityRepository availabilityRepository()
			throws Exception {
		return new XMLAvailabilityRepository();
	}

	@Bean
	public ProgramSheetRepository programSheetRepository()
			throws Exception {
		return new XMLProgramSheetRepository();
	}

	@Bean
	public UserSecurityService userSecurityService()
			throws Exception {
		return new UserSecurityService();
	}

	@Bean
	public ConcomittingCoursesFilter concomittingCoursesFilter() {
		return new ConcomittingCoursesFilter();
	}

	@Bean
	public CourseLevelFilter courseLevelFilter() {
		return new CourseLevelFilter();
	}

	@Bean
	public SameTeacherFilter sameTeacherFilter() {
		return new SameTeacherFilter();
	}

	@Bean
	public UnavailableTeacherFilter unavailableTeacherFilter() {
		return new UnavailableTeacherFilter();
	}

	@Bean
	public ConflictDetector conflictDetector() {
		return new ConflictDetector();
	}

	@Bean
	public ScheduleGenerator scheduleGenerator() {
		return new ScheduleGenerator();
	}

	@Bean
	public AddNotificationAspect modifiedSectionAspect() {
		return new AddNotificationAspect();
	}

	@Bean
	public SendEmailOnModifyAspect sendEmailOnModifyAspect() {
		return new SendEmailOnModifyAspect();
	}

	@Bean
	public DetectConflictAspect detectConflictAspect() {
		return new DetectConflictAspect();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ResourcesLoader resourcesLoader() {
		return new ResourcesLoader();
	}

	public JavaMailSenderImpl mailSender() {
		ConfigManager configManager = ConfigManager.getConfigManager();

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(configManager.getSMTPServerHost());
		mailSender.setPort(configManager.getSMTPServerPort());
		mailSender.setUsername(configManager.getSMTPServerUsername());
		mailSender.setPassword(configManager.getSMTPServerPassword());
		mailSender.setProtocol(configManager.getSMTPServerProtocol());

		return mailSender;
	}

	@Bean
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
