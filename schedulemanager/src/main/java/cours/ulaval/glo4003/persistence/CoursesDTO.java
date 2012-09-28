package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.model.Course;

@XmlRootElement(name = "courses")
public class CoursesDTO {

	private List<Course> courses = new ArrayList<Course>();

	@XmlElement(name = "course")
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
