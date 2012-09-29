package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import cours.ulaval.glo4003.model.Offering;

public class XMLOfferingRepositoryIT {

	
	
	@Test
	public void canGetOfferings() throws Exception {
		XMLOfferingRepository repository = new XMLOfferingRepository();
	
		ArrayList<String> years = repository.findYears();
		Offering offering = repository.find(years.get(1));
		
		for(String offer : offering.getOffering()) {
			System.out.println(offer);
		}
		
		assertEquals("2011-2012", offering.getYear());
		assertTrue(offering.getOffering().contains("IFT-2007"));
	}
	
	
}
