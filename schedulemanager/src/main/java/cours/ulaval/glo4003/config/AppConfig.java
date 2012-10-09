package cours.ulaval.glo4003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cours.ulaval.glo4003.domain.CourseRepository;
import cours.ulaval.glo4003.domain.OfferingRepository;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;
import cours.ulaval.glo4003.persistence.XMLOfferingRepository;

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
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
