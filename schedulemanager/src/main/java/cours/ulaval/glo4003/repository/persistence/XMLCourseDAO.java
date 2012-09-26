package cours.ulaval.glo4003.repository.persistence;

import javax.xml.bind.JAXBException;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseDAO {

	private XMLSerializer<CoursesPool> serializer;
	private ConfigManager resourcesPaths;

	public XMLCourseDAO() throws JAXBException {
		serializer = new XMLSerializer<CoursesPool>(CoursesPool.class);
		resourcesPaths = ConfigManager.getConfigManager();
	}

	public CoursesPool getCourses() throws JAXBException, DAOException {
		return serializer.deserialize(resourcesPaths.getCoursesFilePath());
	}

	public void setSerializer(XMLSerializer<CoursesPool> serializer) {
		this.serializer = serializer;
	}
}
