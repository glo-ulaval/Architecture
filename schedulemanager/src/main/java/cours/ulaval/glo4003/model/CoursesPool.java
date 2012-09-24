package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "courses")
public class CoursesPool {

	@XmlElement(name = "course")
	private List<Course> courses = new ArrayList<Course>();

	public int getCoursesCount() {
		return courses.size();
	}
}
