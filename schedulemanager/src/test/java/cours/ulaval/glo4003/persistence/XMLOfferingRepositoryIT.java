package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import cours.ulaval.glo4003.model.Offering;

public class XMLOfferingRepositoryIT {
	
	@Test
	public void canGetOfferings() throws Exception {
		XMLOfferingRepository repository = new XMLOfferingRepository();
	
		ArrayList<String> years = repository.findYears();
		Offering offering = repository.find(years.get(1));
		
		
		assertTrue(years.contains("2011-2012"));
		assertNotNull(offering);
		assertEquals("2011-2012", offering.getYear());
		assertEquals(2, years.size());
		
		//PASSE PAS, TROUVER COMMENT PARSER LISTE - 
		//créer un objet acronym qui contient juste un string.
		assertEquals(2, offering.getAcronyms().size());  
		assertTrue(offering.getAcronyms().contains("GLO-2901"));
	}
	
	
}
