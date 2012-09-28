package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Course {

	private String acronym;
	private String title;
	private int credits;
	private String description;
	private Cycle cycle;

	private List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();

	public Course() {
		super();
	}

	public Course(String acronym, String title, int credits, String description, Cycle cycle,
			List<Prerequisite> prerequisites) {
		super();
		this.acronym = acronym;
		this.title = title;
		this.credits = credits;
		this.description = description;
		this.cycle = cycle;
		this.prerequisites = prerequisites;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCredits() {
		return credits;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "prerequisite")
	public List<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Prerequisite> prerequisites) {
		this.prerequisites = prerequisites;
	}
}
