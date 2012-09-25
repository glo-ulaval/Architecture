package cours.ulaval.glo4003.persistence;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;

import cours.ulaval.glo4003.model.CourseOffering;
import cours.ulaval.glo4003.repository.CourseOfferingRetriever;

public class CourseOfferingBeanRetriever implements CourseOfferingRetriever{

	@Autowired
	@Qualifier("org.springframework.oxm.castor.CastorMarshaller")
	private Unmarshaller unmarshaller;

	@Override
	public Map<String, CourseOffering> getOfferings() {
		return null;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
}
