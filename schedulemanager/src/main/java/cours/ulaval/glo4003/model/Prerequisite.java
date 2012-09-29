package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Prerequisite {

	@XmlElementWrapper(name = "acronyms")
	@XmlElement(name = "acronym")
	private List<String> acronyms = new ArrayList<String>();

	private Boolean isConcomitant = false;

	public List<String> getAcronyms() {
		return acronyms;
	}

	public void setAcronyms(List<String> acronyms) {
		this.acronyms = acronyms;
	}

	public Boolean isConcomitant() {
		return isConcomitant;
	}

	public void setIsConcomitant(Boolean isConcomitant) {
		this.isConcomitant = isConcomitant;
	}
}
