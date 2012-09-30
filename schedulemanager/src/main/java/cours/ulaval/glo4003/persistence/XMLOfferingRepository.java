package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;

import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLOfferingRepository implements OfferingRepository {

	private HashMap<String, Offering> offerings = new HashMap<String, Offering>();

	public XMLOfferingRepository() throws Exception {
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
	public void delete(String year) {
		offerings.remove(year);
	}

	private void parseXML() throws Exception {
		XMLSerializer<OfferingDTO> serializer = new XMLSerializer<OfferingDTO>(OfferingDTO.class);
		ArrayList<Offering> deserializedOfferings = serializer.deserialize(
				ConfigManager.getConfigManager().getOfferingsFilePath()).getOfferings();
		for (Offering offering : deserializedOfferings) {
			offerings.put(offering.getYear(), offering);
		}
	} 
	
	private void saveXML() throws Exception {
		XMLSerializer<OfferingDTO> serializer = new XMLSerializer<OfferingDTO>(OfferingDTO.class);
		
		ArrayList<Offering> newOfferings = new ArrayList<Offering>();
		for(Offering offering : offerings.values()) {
			newOfferings.add(offering);
		}
		
		OfferingDTO offeringDTO = new OfferingDTO();
		offeringDTO.setOfferings(newOfferings);
		serializer.serialize(offeringDTO, ConfigManager.getConfigManager().getOfferingsFilePath());		
	}
}
