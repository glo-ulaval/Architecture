package cours.ulaval.glo4003.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.repository.persistence.XMLCourseDAO;

public class CourseRepository {

	@Autowired
	@Qualifier("XMLCourseDAO")
	private XMLCourseDAO courseDAO;

	public void setCourseDAO(XMLCourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public XMLCourseDAO getCourseDAO() {
		return courseDAO;
	}

	public CoursesPool getAll() throws Exception {
		return courseDAO.getCourses();
	}
}
