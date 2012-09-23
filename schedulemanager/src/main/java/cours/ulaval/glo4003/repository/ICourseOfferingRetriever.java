package cours.ulaval.glo4003.repository;

import java.util.Map;

import org.springframework.stereotype.Component;

import cours.ulaval.glo4003.model.CourseOffering;

@Component
public interface ICourseOfferingRetriever {

	Map<String, CourseOffering> getOfferings();
	
}
