package cours.ulaval.glo4003.repository.persistence;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;

import cours.ulaval.glo4003.model.Offering;

public class XMLCourseOfferingDAO {

	@Autowired
	@Qualifier("org.springframework.oxm.castor.CastorMarshaller")
	private Unmarshaller unmarshaller;

	public Map<String, Offering> getOfferings() {
		return null;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

}
