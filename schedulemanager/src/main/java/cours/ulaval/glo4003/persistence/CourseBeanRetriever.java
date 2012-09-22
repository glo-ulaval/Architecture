package cours.ulaval.glo4003.persistence;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Marshaller;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.repository.ICourseRetriever;

public class CourseBeanRetriever implements ICourseRetriever {

	@Autowired
	@Qualifier("org.springframework.oxm.castor.CastorMarshaller")
	private Marshaller marshaller;

	@Override
	public Collection<Course> getCourses() {
		return null;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
}
