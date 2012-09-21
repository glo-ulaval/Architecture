package cours.ulaval.glo4003.controllers;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AppConfig {

	@Bean
	ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		int variable = 0;
		return resolver;
	}

}
