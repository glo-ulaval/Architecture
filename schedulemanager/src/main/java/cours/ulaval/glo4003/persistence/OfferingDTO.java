package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.model.Offering;

@XmlRootElement(name = "courseOfferings")
public class OfferingDTO {

	private List<Offering> offerings = new ArrayList<Offering>();

	public void setOfferings(List<Offering> offerings) {
		this.offerings = offerings;
	}

	public List<Offering> getOfferings() {
		return this.offerings;
	}

}
