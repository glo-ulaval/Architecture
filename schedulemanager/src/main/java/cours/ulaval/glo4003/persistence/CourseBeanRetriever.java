package cours.ulaval.glo4003.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.repository.ICourseRetriever;

public class CourseBeanRetriever implements ICourseRetriever {

	@Autowired
	private Marshaller marshaller;

	@Override
	public Iterable<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
}
