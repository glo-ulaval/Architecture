package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("rawtypes")
@XmlRootElement(name = "courses")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoursesPool implements Iterable {

	@XmlElement(name = "course")
	private List<Course> courses = new ArrayList<Course>();

	public int getCoursesCount() {
		return courses.size();
	}

	public Iterator<Course> iterator() {
		return courses.iterator();
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
