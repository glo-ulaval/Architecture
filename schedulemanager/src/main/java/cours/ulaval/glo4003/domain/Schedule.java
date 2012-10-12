package cours.ulaval.glo4003.domain;

import java.util.HashMap;
import java.util.Map;

public class Schedule {

	private String id;
	private String year;
	private Semester semester;
	private Map<String, Section> sections;
	private String personInCharge;

	public Schedule() {
	}

	public Schedule(String id) {
		this.id = id;
		sections = new HashMap<String, Section>();
	}

	public void add(Section section) {
		if (!sectionExist(section.getNrc())) {
			sections.put(section.getNrc(), section);
		}
	}

	public void delete(String sectionNrc) {
		sections.remove(sectionNrc);
	}

	private boolean sectionExist(String nrc) {
		return sections.containsKey(nrc);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Map<String, Section> getSections() {
		return sections;
	}

	public void setSections(Map<String, Section> sections) {
		this.sections = sections;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

}
