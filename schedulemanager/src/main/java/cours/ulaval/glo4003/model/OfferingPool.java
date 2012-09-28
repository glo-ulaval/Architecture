package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferingPool {

	Map<String, ArrayList<String>> courseOfferings = new HashMap<String, ArrayList<String>>();

	public void setCourseOfferings(HashMap<String, ArrayList<String>> offerings) {
		courseOfferings = offerings;
	}

	public List<String> getOffering(String year) {
		return courseOfferings.get(year);
	}

	public int getOfferingCount() {
		return courseOfferings.size();
	}

}
