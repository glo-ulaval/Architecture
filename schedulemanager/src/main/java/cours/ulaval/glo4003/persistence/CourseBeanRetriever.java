package cours.ulaval.glo4003.persistence;

import java.io.InputStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.repository.ICourseRetriever;

public class CourseBeanRetriever implements ICourseRetriever {

	private Unmarshaller unmarshaller;

	public CourseBeanRetriever() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Course.class);
		unmarshaller = context.createUnmarshaller();
	}

	@Override
	public Collection<Course> getCourses() throws JAXBException {
		InputStream stream = CourseBeanRetriever.class
				.getResourceAsStream(ResourcesNames.COURSES_FILE);
		Course course = (Course) unmarshaller.unmarshal(stream);

		return null;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
