package cours.ulaval.glo4003.model;

import java.util.HashMap;

public class OfferingPool {

	HashMap<String, Offering> courseOfferings = new HashMap<String, Offering>();

	public void setCourseOfferings(HashMap<String, Offering> offerings) {
		courseOfferings = offerings;
	}

	public Offering getOffering(String year) {
		return courseOfferings.get(year);
	}

	public int getOfferingCount() {
		return courseOfferings.size();
	}

	public void saveOffering(Offering offering) {
		courseOfferings.put(offering.getYear(), offering);
	}

	public boolean contains(Offering offering) {
		return courseOfferings.containsValue(offering);
	}

}
