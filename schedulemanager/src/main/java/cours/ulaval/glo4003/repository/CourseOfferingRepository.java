package cours.ulaval.glo4003.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.persistence.XMLCourseOfferingDAO;

public class CourseOfferingRepository {

	@Autowired
	@Qualifier("XMLCourseOfferingDAO")
	private XMLCourseOfferingDAO offeringRetriever;

	public Map<String, Offering> getAll() {

		return offeringRetriever.getOfferings();
	}

	public XMLCourseOfferingDAO getOfferingRetriever() {
		return offeringRetriever;
	}

	public void setOfferingRetriever(XMLCourseOfferingDAO offeringRetriever) {
		this.offeringRetriever = offeringRetriever;
	}
}
