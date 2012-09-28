package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class OfferingPoolTest {

	private static final String YEAR_OF_OFFERING = "1";
	private static final int NUMBER_OF_OFFERINGS = 4;

	private HashMap<String, Offering> offerings;
	private OfferingPool pool;
	
	@Before
	public void setUp() {
		offerings = getOfferings();
		pool = new OfferingPool();
		pool.setCourseOfferings(offerings);
	}
	
	@Test
	public void canInstantiate() {
		
		assertNotNull(pool);
	}
	
	@Test
	public void canGetAnOffering() {
		
		assertEquals(offerings.get(YEAR_OF_OFFERING), pool.getOffering(YEAR_OF_OFFERING));
	}
	
	@Test
	public void canSaveAnOffering() {
		Offering offering = mock(Offering.class);
		
		pool.saveOffering(offering);
		
		assertTrue(pool.contains(offering));
		
	}
	
	@Test
	public void canGetOfferingCount() {
		
		assertEquals(NUMBER_OF_OFFERINGS, pool.getOfferingCount());
	}
	
	private HashMap<String, Offering> getOfferings() {
		Offering offering = mock(Offering.class);
		
		HashMap<String, Offering> offerings = new HashMap<String, Offering>();
		
		for (Integer i=0; i < NUMBER_OF_OFFERINGS; i++) {
			offerings.put(i.toString(), offering);
		}
		
		return offerings;
	}
	
}
