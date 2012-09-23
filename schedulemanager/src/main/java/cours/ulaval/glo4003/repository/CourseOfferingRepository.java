package cours.ulaval.glo4003.repository;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.model.CourseOffering;

public class CourseOfferingRepository {

	@Autowired
	@Qualifier("CourseOfferingBeanRetriever")
	private ICourseOfferingRetriever offeringRetriever;
	
	public Map<String, CourseOffering> getAll() {
		
		return offeringRetriever.getOfferings();
	}

	public ICourseOfferingRetriever getOfferingRetriever() {
		return offeringRetriever;
	}

	public void setOfferingRetriever(ICourseOfferingRetriever offeringRetriever) {
		this.offeringRetriever = offeringRetriever;
	}
}
