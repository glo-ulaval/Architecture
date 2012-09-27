package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CourseOfferingPoolTest {

	private static final String YEAR_OF_OFFERING = "1";
	private static final String ANOTHER_COURSE_NAME = "un autre cours";
	private static final String COURSE_NAME = "un cours";
	private static final int NUMBER_OF_OFFERINGS = 4;

	private HashMap<String, ArrayList<String>> offerings;
	private CourseOfferingPool pool;
	
	@Before
	public void setUp() {
		offerings = getOfferings();
		pool = new CourseOfferingPool();
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
	public void canGetOfferingCount() {
		
		assertEquals(NUMBER_OF_OFFERINGS, pool.getOfferingCount());
	}
	
	private HashMap<String, ArrayList<String>> getOfferings() {
		
		ArrayList<String> array = new ArrayList<String>();
		array.add(COURSE_NAME);
		array.add(ANOTHER_COURSE_NAME);
		
		HashMap<String, ArrayList<String>> offerings = new HashMap<String, ArrayList<String>>();
		
		for (Integer i=0; i < NUMBER_OF_OFFERINGS; i++) {
			offerings.put(i.toString(), array);
		}
		
		return offerings;
	}
	
}
