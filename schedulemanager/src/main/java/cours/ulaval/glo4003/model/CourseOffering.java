package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Collection;

public class CourseOffering {

	private Collection<Course> offering = new ArrayList<Course>();
	private String year; // 2012-2013 from autumn ?
	
	public Collection<Course> getOffering() {
		return offering;
	}
	
	public void setOffering(Collection<Course> courses) {
		this.offering = courses;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
