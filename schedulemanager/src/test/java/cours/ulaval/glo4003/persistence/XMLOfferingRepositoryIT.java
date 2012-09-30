package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Offering;

public class XMLOfferingRepositoryIT {

	private static final String STORED_OFFERING_YEAR = "2011-2012";
	private static final String NEW_OFFERING_YEAR = "2009-2010";
	XMLOfferingRepository repository;
	
	@Before
	public void setUp() throws Exception {
		repository = new XMLOfferingRepository();
	}
	
	@Test
	public void canGetOfferings() {
		ArrayList<String> years = repository.findYears();		
		Offering offering = repository.find(years.get(years.indexOf(STORED_OFFERING_YEAR)));

		assertTrue(years.contains(STORED_OFFERING_YEAR));
		assertNotNull(offering);
		assertEquals(STORED_OFFERING_YEAR, offering.getYear());
		assertEquals(5, offering.getAcronyms().size());
		assertTrue(offering.getAcronyms().contains("IFT-1904"));
	}
	
	@Test
	public void canSaveAnOffering() throws Exception {		
		Offering offering = new Offering();
		ArrayList<String> acronyms = new ArrayList<String>();
		acronyms.add("IFT-1001");
		acronyms.add("IFT-1002");
		acronyms.add("IFT-1003");
		acronyms.add("IFT-1004");
		acronyms.add("IFT-1005");
		offering.setOffering(acronyms);
		offering.setYear(NEW_OFFERING_YEAR);
		
		repository.store(offering);
		
		XMLOfferingRepository refreshedRepository = new XMLOfferingRepository();
		
		ArrayList<String> storedOfferingYears = refreshedRepository.findYears();
		Offering storedOffering = refreshedRepository.find(NEW_OFFERING_YEAR);
		
		
		assertTrue(storedOfferingYears.contains(NEW_OFFERING_YEAR));
		assertTrue(storedOffering.getAcronyms().contains("IFT-1001"));	
	}
}
