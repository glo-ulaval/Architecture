package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.exception.InvalidOfferingOperation;

public class Offering {
	private String year;
	private List<String> fallOffering = new ArrayList<String>();
	private List<String> winterOffering = new ArrayList<String>();
	private List<String> summerOffering = new ArrayList<String>();

	public Offering() {
	}

	public Offering(String year) {
		this.year = year;
	}

	public void addCourse(String acronym, Semester semester)
			throws InvalidOfferingOperation {
		if (!getBySemester(semester).contains(acronym)) {
			getBySemester(semester).add(acronym);
		} else {
			throw new InvalidOfferingOperation("Le cours " + acronym + " est déjà dans l'offre de cours de l'année " + year);
		}
	}

	public void removeCourse(String acronym, Semester semester)
			throws InvalidOfferingOperation {
		if (!getBySemester(semester).remove(acronym)) {
			throw new InvalidOfferingOperation("Impossible de retirer le cours " + acronym + " de l'offre de cours de l'année "
					+ year);
		}
	}

	public List<String> getBySemester(Semester semester) {
		if (semester == Semester.Automne) {
			return fallOffering;
		} else if (semester == Semester.Hiver) {
			return winterOffering;
		} else {
			return summerOffering;
		}
	}

	public void setBySemester(Semester semester, ArrayList<String> acronyms) {
		if (semester == Semester.Automne) {
			fallOffering = acronyms;
		} else if (semester == Semester.Hiver) {
			winterOffering = acronyms;
		} else {
			summerOffering = acronyms;
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getFallOffering() {
		return fallOffering;
	}

	public void setFallOffering(List<String> fallOffering) {
		this.fallOffering = fallOffering;
	}

	public List<String> getWinterOffering() {
		return winterOffering;
	}

	public void setWinterOffering(List<String> winterOffering) {
		this.winterOffering = winterOffering;
	}

	public List<String> getSummerOffering() {
		return summerOffering;
	}

	public void setSummerOffering(List<String> summerOffering) {
		this.summerOffering = summerOffering;
	}

}
