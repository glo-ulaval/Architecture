package cours.ulaval.glo4003.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Prerequisite {

	@XmlElement(name = "acronym")
	private ArrayList<String> courses = new ArrayList<String>();

	public ArrayList<String> getCourses() {
		return courses;
	}

}
