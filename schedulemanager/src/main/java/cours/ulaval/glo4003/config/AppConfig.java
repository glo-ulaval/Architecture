package cours.ulaval.glo4003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cours.ulaval.glo4003.controller.security.UserSecurityService;
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

@Configuration
public class AppConfig {

	@Bean
	public CourseRepository courseRepository() throws Exception {
		return new XMLCourseRepository();
	}

	@Bean
	public OfferingRepository offeringRepository() throws Exception {
		return new XMLOfferingRepository();
	}

	@Bean
	public ScheduleRepository scheduleRepository() throws Exception {
		return new XMLScheduleRepository();
	}

	@Bean
	public UserRepository userRepository() throws Exception {
		return new XMLUserRepository();
	}

	@Bean
	public AvailabilityRepository availabilityRepository() throws Exception {
		return new XMLAvailabilityRepository();
	}

	@Bean
	public ProgramSheetRepository programSheetRepository() throws Exception {
		return new XMLProgramSheetRepository();
	}

	@Bean
	public UserSecurityService userSecurityService() throws Exception {
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
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
