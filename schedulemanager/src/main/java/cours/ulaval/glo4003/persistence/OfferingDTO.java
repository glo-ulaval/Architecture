package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;

import cours.ulaval.glo4003.model.Offering;


public class OfferingDTO {

	private ArrayList<Offering> offerings = new ArrayList<Offering>();
	
	public void setOfferings(ArrayList<Offering> offerings) {
		this.offerings = offerings;
	}
	
	public ArrayList<Offering> getOfferings() {
		return this.offerings;
	}
	
}
