package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.domain.Availability;

public class XMLAvailabilityRepositoryTest {

	private static final String UN_IDUL = "un_idul";
	@Mock
	Availability availability;
	@Mock
	XMLSerializer<AvailabilityXMLWrapper> serializer;
	@InjectMocks
	XMLAvailabilityRepository repo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(availability.getIdul()).thenReturn(UN_IDUL);
	}

	@Test
	public void canInstantiate()
			throws Exception {

		assertNotNull(repo);
	}

	@Test
	public void canFindAnAvailabilityWithAnIdul() {
		repo.store(availability);

		assertEquals(availability, repo.findByIdul(UN_IDUL));
	}

}
