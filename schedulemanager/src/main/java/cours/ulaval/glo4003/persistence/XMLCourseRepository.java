package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepository implements CourseRepository {

	private HashMap<String, Course> courses = new HashMap<String, Course>();
	private XMLSerializer<CoursesDTO> serializer;

	public XMLCourseRepository() throws Exception {
		serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
		parseXML();
	}

	public List<Course> findAll() throws Exception {
		return new ArrayList<Course>(courses.values());
	}

	private void parseXML() throws Exception {
		List<Course> deserializedCourses = serializer
				.deserialize(ConfigManager.getConfigManager().getCoursesFilePath()).getCourses();
		System.out.println("Deserialized");
		for (Course course : deserializedCourses) {
			courses.put(course.getAcronym(), course);
		}
	}

	public Course findByAcronym(String acronym) {
		return courses.get(acronym);
	}

	// Do not use : for test purpose only
	protected XMLCourseRepository(XMLSerializer<CoursesDTO> serializer) throws Exception {
		this.serializer = serializer;
		parseXML();
	}
}
