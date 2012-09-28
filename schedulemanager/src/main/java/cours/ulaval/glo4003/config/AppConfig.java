package cours.ulaval.glo4003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;

@Configuration
public class AppConfig {

	@Bean
	public CourseRepository courseRepository() throws Exception {
		return new XMLCourseRepository();
	}

	@Bean
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
