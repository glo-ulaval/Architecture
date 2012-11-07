package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.repository.OfferingRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLOfferingRepository implements OfferingRepository {

	private XMLSerializer<OfferingXMLWrapper> serializer;
	private Map<String, Offering> offerings = new HashMap<String, Offering>();

	public XMLOfferingRepository() throws Exception {
		serializer = new XMLSerializer<OfferingXMLWrapper>(OfferingXMLWrapper.class);
		parseXML();
	}

	@Override
	public List<String> findYears() {
		List<String> years = new ArrayList<String>();

		for (String x : offerings.keySet()) {
			years.add(x);
		}

		return years;
	}

	@Override
	public Offering find(String year) {
		return offerings.get(year);
	}

	@Override
	public Boolean containsOfferingFor(String year) {
		return offerings.containsKey(year);
	}

	@Override
	public void store(Offering offering) throws Exception {
		offerings.put(offering.getYear(), offering);
		saveXML();
	}

	@Override
	public void delete(String year, Semester semester) throws Exception {
		offerings.remove(year);
		saveXML();
	}

	@Override
	public void delete(String year) throws Exception {
		offerings.remove(year);
		saveXML();
	}

	private void parseXML() throws Exception {
		List<Offering> deserializedOfferings = serializer.deserialize(ConfigManager.getConfigManager().getOfferingsFilePath()).getOfferings();
		for (Offering offering : deserializedOfferings) {
			offerings.put(offering.getYear(), offering);
		}
	}

	private void saveXML() throws Exception {
		OfferingXMLWrapper offeringDTO = new OfferingXMLWrapper();
		List<Offering> offeringList = new ArrayList<Offering>();
		for (Offering offering : offerings.values()) {
			offeringList.add(offering);
		}
		offeringDTO.setOfferings(offeringList);
		serializer.serialize(offeringDTO, ConfigManager.getConfigManager().getOfferingsFilePath());
	}

	// DO NOT USE -- for test purpose only
	protected XMLOfferingRepository(XMLSerializer<OfferingXMLWrapper> mockedSerializer) throws Exception {
		serializer = mockedSerializer;
	}

	protected void clearAll() throws Exception {
		offerings.clear();
		saveXML();
	}
}
