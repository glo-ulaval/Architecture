package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
		try {
			repository = new XMLOfferingRepository();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		offering = mock(Offering.class);
		when(offering.getYear()).thenReturn(VALID_YEAR);
	}

	@Test
	public void canInstantiateOfferingRepository() {
		assertNotNull(repository);
	}

	@Test
	public void canStoreAnOffering() throws Exception {
		repository.store(offering);

		assertEquals(offering, repository.find(VALID_YEAR));
	}

	@Test
	public void canFindAnOffering() throws Exception {
		repository.store(offering);

		assertEquals(offering, repository.find(VALID_YEAR));
	}

	@Test
	public void canFindYearsOfOfferings() throws Exception {
		ArrayList<String> years = new ArrayList<String>();
		years.add(VALID_YEAR);

		repository.store(offering);

		assertNotNull(repository.findYears());
		assertTrue(repository.findYears().contains(VALID_YEAR));
	}

	@Test
	public void canDeleteAnOffering() throws Exception {
		repository.store(offering);

		repository.delete(VALID_YEAR);

		assertNull(repository.find(VALID_YEAR));
	}
}
