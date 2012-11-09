package cours.ulaval.glo4003.domain;

import java.util.HashMap;
import java.util.Map;

public class ProgramSheet {

	private Map<String, Integer> courses = new HashMap<String, Integer>();

	public ProgramSheet() {

	}

	public ProgramSheet(Map<String, Integer> courses) {
		this.courses = courses;
	}

	public Map<String, Integer> getCourses() {
		return courses;
	}

	public void setCourses(Map<String, Integer> courses) {
		this.courses = courses;
	}

	public boolean areCoursesSameLevel(String firstAcronym, String secondAcronym) {
		return courses.get(firstAcronym) == courses.get(secondAcronym);
	}

}
