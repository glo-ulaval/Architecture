package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLCourseRepository implements CourseRepository {

	private Map<String, Course> courses = new HashMap<String, Course>();
	private XMLSerializer<CoursesXMLWrapper> serializer;

	public XMLCourseRepository()
			throws Exception {
		serializer = new XMLSerializer<CoursesXMLWrapper>(CoursesXMLWrapper.class);
		parseXML();
	}

	public List<Course> findAll()
			throws Exception {
		return new ArrayList<Course>(courses.values());
	}

	private void parseXML()
			throws Exception {
		List<Course> deserializedCourses = serializer.deserialize(ConfigManager.getConfigManager().getCoursesFilePath())
				.getCourses();
		for (Course course : deserializedCourses) {
			courses.put(course.getAcronym(), course);
		}
	}

	public Course findByAcronym(String acronym) {
		return courses.get(acronym);
	}

	@Override
	public List<Course> findByOffering(Offering offering) {
		List<Course> courses = new ArrayList<Course>();
		for (String acronym : offering.getOffering()) {
			courses.add(findByAcronym(acronym));
		}
		return courses;
	}

	@Override
	public void store(Course course) {
		if (!courses.containsKey(course.getAcronym())) {
			courses.put(course.getAcronym(), course);
		}
		saveXML();
	}

	private void saveXML() {
		CoursesXMLWrapper coursesDTO = new CoursesXMLWrapper();
		coursesDTO.setCourses(new ArrayList<Course>(courses.values()));
		try {
			serializer.serialize(coursesDTO, ConfigManager.getConfigManager().getCoursesFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Do not use : for test purpose only
	protected XMLCourseRepository(XMLSerializer<CoursesXMLWrapper> serializer)
			throws Exception {
		this.serializer = serializer;
		parseXML();
	}

}
