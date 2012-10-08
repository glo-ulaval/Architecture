package cours.ulaval.glo4003.model;

import java.util.ArrayList;

public class Schedule {

	private String session;
	private List<CourseSection> coursesSections;
	private String personInCharge;

	public Schedule() {
		coursesSections = new ArrayList<CourseSection>();
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public List<CourseSection> getCoursesSections() {
		return coursesSections;
	}

	public void setCoursesSections(List<CourseSection> coursesSections) {
		this.coursesSections = coursesSections;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

}
