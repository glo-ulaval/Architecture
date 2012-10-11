package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.domain.Availability;

@XmlRootElement(name = "Availabilities")
public class AvailabilityXMLWrapper {

	private List<Availability> availabilities = new ArrayList<Availability>();

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

}
