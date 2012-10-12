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
	private Map<String, Map<Semester, Offering>> offerings = new HashMap<String, Map<Semester, Offering>>();

	public XMLOfferingRepository()
			throws Exception {
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
	public Offering find(String year, Semester semester) {
		return offerings.get(year).get(semester);
	}

	@Override
	public Boolean containsOfferingFor(String year, Semester semester) {
		if (!offerings.containsKey(year) || !offerings.get(year).containsKey(semester)) {
			return false;
		}
		return true;
	}

	@Override
	public void store(Offering offering)
			throws Exception {
		if (!offerings.containsKey(offering.getYear())) {
			offerings.put(offering.getYear(), new HashMap<Semester, Offering>());
		}
		offerings.get(offering.getYear()).put(offering.getSemester(), offering);
		saveXML();
	}

	@Override
	public void delete(String year, Semester semester)
			throws Exception {
		offerings.get(year).remove(semester);
		saveXML();
	}

	@Override
	public void delete(String year)
			throws Exception {
		offerings.remove(year);
		saveXML();
	}

	private void parseXML()
			throws Exception {
		List<Offering> deserializedOfferings = serializer.deserialize(ConfigManager.getConfigManager().getOfferingsFilePath())
				.getOfferings();
		for (Offering offering : deserializedOfferings) {
			if (!offerings.containsKey(offering.getYear())) {
				offerings.put(offering.getYear(), new HashMap<Semester, Offering>());
			}
			offerings.get(offering.getYear()).put(offering.getSemester(), offering);
		}
	}

	private void saveXML()
			throws Exception {
		OfferingXMLWrapper offeringDTO = new OfferingXMLWrapper();
		List<Offering> offeringList = new ArrayList<Offering>();
		for (Map<Semester, Offering> map : offerings.values()) {
			offeringList.addAll(map.values());
		}
		offeringDTO.setOfferings(offeringList);
		serializer.serialize(offeringDTO, ConfigManager.getConfigManager().getOfferingsFilePath());
	}
}
