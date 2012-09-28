package cours.ulaval.glo4003.config;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cours.ulaval.glo4003.repository.persistence.XMLCourseDAO;

@Configuration
public class AppConfig {

	@Bean
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	XMLCourseDAO xmlCourseDao() throws JAXBException {
		return new XMLCourseDAO();
	}
}
