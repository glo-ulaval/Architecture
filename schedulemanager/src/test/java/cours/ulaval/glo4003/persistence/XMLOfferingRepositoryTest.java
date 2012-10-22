package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;

public class XMLOfferingRepositoryTest {
	private static final String VALID_YEAR = "2011";
	private static final Semester A_SEMESTER = Semester.Ete;

	@Mock
	private Offering offering;
	@Mock
	private XMLSerializer<OfferingXMLWrapper> mockedSerializer;
	private XMLOfferingRepository repository;

	@Before
	public void setUp()
			throws Exception {
		MockitoAnnotations.initMocks(this);
		when(offering.getYear()).thenReturn(VALID_YEAR);
		repository = new XMLOfferingRepository(mockedSerializer);
	}

	@Test
	public void canInstantiateOfferingRepository() {
		assertNotNull(repository);
	}

	@Test
	public void canStoreAnOffering()
			throws Exception {
		repository.store(offering);

		assertEquals(offering, repository.find(VALID_YEAR));
	}

	@Test
	public void canStoreAnOfferingWhenRepositoryAlreadyContainsYear()
			throws Exception {
		repository.store(offering);

		repository.store(offering);
		assertEquals(offering, repository.find(VALID_YEAR));
	}

	@Test
	public void canFindAnOffering()
			throws Exception {
		repository.store(offering);

		assertEquals(offering, repository.find(VALID_YEAR));
	}

	@Test
	public void canFindYearsOfOfferings()
			throws Exception {
		repository.store(offering);

		assertNotNull(repository.findYears());
		assertTrue(repository.findYears().contains(VALID_YEAR));
	}

	@Test
	public void canDeleteAnOffering()
			throws Exception {
		repository.store(offering);

		repository.delete(VALID_YEAR, A_SEMESTER);

		assertNull(repository.find(VALID_YEAR));
	}

	@Test
	public void canCheckIfContainsOffering()
			throws Exception {
		repository.store(offering);

		assertTrue(repository.containsOfferingFor(VALID_YEAR));
	}
}
