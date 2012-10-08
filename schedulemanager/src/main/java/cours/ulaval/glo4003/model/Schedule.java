package cours.ulaval.glo4003.model;

import java.util.HashMap;
import java.util.Map;

public class Schedule {

	private String id;
	private String session; // Année+saison
	private Map<String, Section> sections;
	private String personInCharge;

	public Schedule(String id) {
		this.id = id;
		sections = new HashMap<String, Section>();
	}

	public void add(Section section) {
		if (!sectionExist(section)) {
			sections.put(section.toString(), section);
		}
	}

	private boolean sectionExist(Section section) {
		return sections.containsKey(section.toString());
	}

	public String getId() {
		return id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Map<String, Section> getCoursesSections() {
		return sections;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

}
