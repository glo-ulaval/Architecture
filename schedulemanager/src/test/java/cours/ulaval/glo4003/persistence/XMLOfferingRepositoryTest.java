package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.domain.Offering;

public class XMLOfferingRepositoryTest {
	private static final String VALID_YEAR = "2011";

	@Mock
	private XMLSerializer<OfferingXMLWrapper> serializer;
	@Mock
	private Offering offering;
	@InjectMocks
	private XMLOfferingRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
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
