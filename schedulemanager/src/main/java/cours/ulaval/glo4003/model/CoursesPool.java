package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("rawtypes")
@XmlRootElement(name = "courses")
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
	
	public List<Course> getList(){
		return courses;
	}
}
