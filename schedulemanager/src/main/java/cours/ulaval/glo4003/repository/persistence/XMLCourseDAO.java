package cours.ulaval.glo4003.repository.persistence;

import javax.xml.bind.JAXBException;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ResourcesPaths;
import cours.ulaval.glo4003.utils.XMLSerializer;

public class XMLCourseDAO {

	private XMLSerializer<CoursesPool> serializer;

	public XMLCourseDAO() throws JAXBException {
		serializer = new XMLSerializer<CoursesPool>(CoursesPool.class);
	}

	public CoursesPool getCourses() throws JAXBException, DAOException {
		return serializer.deserialize(ResourcesPaths.COURSES_FILE);
	}

	public void setSerializer(XMLSerializer<CoursesPool> serializer) {
		this.serializer = serializer;
	}
}
