package cours.ulaval.glo4003.model;

import java.util.ArrayList;

public class Offering {

	private ArrayList<String> acronyms = new ArrayList<String>();
	private String year; // 2012-2013 from autumn ?
	
	public Offering() {	
	}
	
	public Offering(String year, Offering courseOffering) {
		this.year = year;
		this.acronyms = courseOffering.getOffering();
		
	}

	public ArrayList<String> getOffering() {
		return acronyms;
	}
	
	public void setOffering(ArrayList<String> courses) {
		this.acronyms = courses;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void addCourse(String acronym) {
		acronyms.add(acronym);	
	}

	public void removeCourse(String acronym) {
		if (!acronyms.remove(acronym)) {
			System.out.println("Impossible de retirer le cours " + acronym + " de l'offre de cours de l'année " + year);
		}
	}
	
}
