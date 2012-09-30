package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;

import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLOfferingRepository implements OfferingRepository {

	private XMLSerializer<OfferingDTO> serializer;
	private HashMap<String, Offering> offerings = new HashMap<String, Offering>();

	public XMLOfferingRepository() throws Exception {
		serializer = new XMLSerializer<OfferingDTO>(OfferingDTO.class);
		parseXML();
	}

	@Override
	public ArrayList<String> findYears() {
		ArrayList<String> years = new ArrayList<String>();

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
	public void store(Offering offering) throws Exception {
		offerings.put(offering.getYear(), offering);
		saveXML();
	}

	@Override
	public void delete(String year) throws Exception {
		offerings.remove(year);
		saveXML();
	}

	private void parseXML() throws Exception {
		ArrayList<Offering> deserializedOfferings = serializer.deserialize(ConfigManager.getConfigManager().getOfferingsFilePath()).getOfferings();
		for (Offering offering : deserializedOfferings) {
			offerings.put(offering.getYear(), offering);
		}
	} 

	private void saveXML() throws Exception {
		OfferingDTO offeringDTO = new OfferingDTO();
		offeringDTO.setOfferings(new ArrayList<Offering>(offerings.values()));
		serializer.serialize(offeringDTO, ConfigManager.getConfigManager().getOfferingsFilePath());		
	}
}
