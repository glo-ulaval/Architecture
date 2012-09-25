package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Prerequisite {

	private List<Acronym> acronyms = new ArrayList<Acronym>();

	public List<Acronym> getAcronyms() {
		return acronyms;
	}

	public void setAcronyms(List<Acronym> acronyms) {
		this.acronyms = acronyms;
	}
}
