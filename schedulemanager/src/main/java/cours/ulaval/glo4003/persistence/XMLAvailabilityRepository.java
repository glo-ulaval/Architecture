package cours.ulaval.glo4003.persistence;

import java.util.HashMap;
import java.util.Map;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.AvailabilityRepository;

public class XMLAvailabilityRepository implements AvailabilityRepository {

	private XMLSerializer<AvailabilityXMLWrapper> serializer;
	private Map<String, Availability> availabilities = new HashMap<String, Availability>();

	public XMLAvailabilityRepository()
			throws Exception {
		serializer = new XMLSerializer<AvailabilityXMLWrapper>(AvailabilityXMLWrapper.class);
	}

	@Override
	public Availability findByIdul(String idul) {
		return availabilities.get(idul);
	}

	@Override
	public void store(Availability availability) {
		this.availabilities.put(availability.getIdul(), availability);
		saveXML();
	}

	private void saveXML() {
		// TODO
	}
}
