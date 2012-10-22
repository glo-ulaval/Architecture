package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.exception.InvalidOfferingOperation;

public class Offering {
	private String year;
	private List<String> offering = new ArrayList<String>();

	public Offering() {
	}

	public Offering(String year) {
		this.year = year;
	}

	public void addCourse(String acronym, Semester semester)
			throws InvalidOfferingOperation {
		if (!offering.contains(acronym)) {
			offering.add(acronym);
		} else {
			throw new InvalidOfferingOperation("Le cours " + acronym + " est déjà dans l'offre de cours de l'année " + year);
		}
	}

	public void removeCourse(String acronym, Semester semester)
			throws InvalidOfferingOperation {
		if (!offering.remove(acronym)) {
			throw new InvalidOfferingOperation("Impossible de retirer le cours " + acronym + " de l'offre de cours de l'année "
					+ year);
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getOffering() {
		return offering;
	}

	public void setOffering(List<String> fallOffering) {
		this.offering = fallOffering;
	}

}
