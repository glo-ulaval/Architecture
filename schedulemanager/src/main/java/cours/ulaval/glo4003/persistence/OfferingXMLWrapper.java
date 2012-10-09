package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.domain.Offering;

@XmlRootElement(name = "courseOfferings")
public class OfferingXMLWrapper {

	private List<Offering> offerings = new ArrayList<Offering>();

	public void setOfferings(List<Offering> offerings) {
		this.offerings = offerings;
	}

	public List<Offering> getOfferings() {
		return this.offerings;
	}

}
