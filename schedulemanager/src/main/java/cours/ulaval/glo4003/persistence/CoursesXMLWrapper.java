package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.domain.Course;

@XmlRootElement(name = "courses")
public class CoursesXMLWrapper {

	private List<Course> courses = new ArrayList<Course>();

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
