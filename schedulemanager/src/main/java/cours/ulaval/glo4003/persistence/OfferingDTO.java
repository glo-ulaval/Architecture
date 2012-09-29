package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.model.Offering;

@XmlRootElement(name = "courseOfferings")
public class OfferingDTO {

	private ArrayList<Offering> offerings = new ArrayList<Offering>();
	
	public void setOfferings(ArrayList<Offering> offerings) {
		this.offerings = offerings;
	}
	
	@XmlElement(name = "offering")
	public ArrayList<Offering> getOfferings() {
		return this.offerings;
	}
	
}
