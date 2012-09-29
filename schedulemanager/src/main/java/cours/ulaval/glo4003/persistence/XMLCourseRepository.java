package cours.ulaval.glo4003.persistence;

import java.util.List;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepository implements CourseRepository {

	private List<Course> courses;
	private XMLSerializer<CoursesDTO> serializer;

	public XMLCourseRepository() throws Exception {
		serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
	}

	public List<Course> findAll() throws Exception {
		if (courses == null) {
			courses = serializer.deserialize(ConfigManager.getConfigManager().getCoursesFilePath()).getCourses();
		}

		return courses;
	}

	public void setSerializer(XMLSerializer<CoursesDTO> serializer) {
		this.serializer = serializer;
	}
}
