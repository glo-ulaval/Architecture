package cours.ulaval.glo4003.persistence;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.repository.ICourseRetriever;

public class CourseBeanRetriever implements ICourseRetriever {

	@Autowired
	@Qualifier("org.springframework.oxm.castor.CastorMarshaller")
	private Unmarshaller unmarshaller;

	@Override
	public Collection<Course> getCourses() {
		return null;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
