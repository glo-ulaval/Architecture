package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.model.exception.InvalidOfferingOperation;

public class Offering {
	private String year;
	private List<String> acronyms = new ArrayList<String>();

	public Offering() {
		super();
	}

	public Offering(String year, Offering courseOffering) {
		this.year = year;
		this.acronyms = courseOffering.getAcronyms();
	}

	public List<String> getAcronyms() {
		return acronyms;
	}

	public void setAcronyms(List<String> acronyms) {
		this.acronyms = acronyms;
	}

	public void setOffering(List<String> courses) {
		this.acronyms = courses;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void addCourse(String acronym)
			throws InvalidOfferingOperation {
		if (!acronyms.contains(acronym)) {
			acronyms.add(acronym);
		} else {
			throw new InvalidOfferingOperation("Le cours " + acronym + " est déjà dans l'offre de cours de l'année " + year);
		}
	}

	public void removeCourse(String acronym)
			throws InvalidOfferingOperation {
		if (!acronyms.remove(acronym)) {
			throw new InvalidOfferingOperation("Impossible de retirer le cours " + acronym + " de l'offre de cours de l'année "
					+ year);
		}
	}
}
