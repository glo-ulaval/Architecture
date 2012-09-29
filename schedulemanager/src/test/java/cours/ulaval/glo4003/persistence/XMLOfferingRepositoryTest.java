package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.Offering;

public class XMLOfferingRepositoryTest {
	
	private static final String VALID_YEAR = "2011";
	private XMLOfferingRepository repository;
	private Offering offering;
	
	
	@Before
	public void setUp() {
		repository = new XMLOfferingRepository();
		offering = mock(Offering.class);
		when(offering.getYear()).thenReturn(VALID_YEAR);
	}
	
	@Test
	public void canInstantiateOfferingRepository() {
		
		assertNotNull(repository);
	}
	
	@Test
	public void canStoreAnOffering() {
		
		repository.store(offering);
		
		assertEquals(offering, repository.find(VALID_YEAR));
	}
	
	@Test
	public void canFindAnOffering() {

		repository.store(offering);
		
		assertEquals(offering, repository.find(VALID_YEAR));
	}
	
	@Test
	public void canFindYearsOfOfferings() {

		ArrayList<String> years = new ArrayList<String>();
		years.add(VALID_YEAR);
		
		repository.store(offering);
		
		assertEquals(years, repository.findYears());
	}
	
	@Test
	public void canDeleteAnOffering() {
		repository.store(offering);
		
		repository.delete(VALID_YEAR);
		
		assertNull(repository.find(VALID_YEAR));
	}
}
