package cours.ulaval.glo4003.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import cours.ulaval.glo4003.controller.security.*;
import cours.ulaval.glo4003.domain.repository.*;
import cours.ulaval.glo4003.persistence.*;

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
	public UserSecurityService userSecurityService() throws Exception {
		return new UserSecurityService();
	}

	@Bean
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
