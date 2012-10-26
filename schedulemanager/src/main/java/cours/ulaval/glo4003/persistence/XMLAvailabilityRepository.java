package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLAvailabilityRepository implements AvailabilityRepository {

	private XMLSerializer<AvailabilityXMLWrapper> serializer;
	private Map<String, Availability> availabilities = new HashMap<String, Availability>();

	public XMLAvailabilityRepository() throws Exception {
		serializer = new XMLSerializer<AvailabilityXMLWrapper>(AvailabilityXMLWrapper.class);
		parseXML();
	}

	@Override
	public Availability findByIdul(String idul) {
		return availabilities.get(idul);
	}

	@Override
	public void store(Availability availability) throws Exception {
		this.availabilities.put(availability.getIdul(), availability);
		saveXML();
	}

	public void parseXML() throws Exception {
		List<Availability> deserializedAvailabilities = serializer.deserialize(ConfigManager.getConfigManager().getAvailabilitiesFilePath())
				.getAvailabilities();
		for (Availability availability : deserializedAvailabilities) {
			availabilities.put(availability.getIdul(), availability);
		}
	}

	private void saveXML() throws Exception {
		AvailabilityXMLWrapper availabilityDTO = new AvailabilityXMLWrapper();
		availabilityDTO.setAvailabilities(new ArrayList<Availability>(availabilities.values()));
		serializer.serialize(availabilityDTO, ConfigManager.getConfigManager().getAvailabilitiesFilePath());
	}
}
