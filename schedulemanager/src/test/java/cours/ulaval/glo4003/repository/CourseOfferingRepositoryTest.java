package cours.ulaval.glo4003.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.CourseOffering;

public class CourseOfferingRepositoryTest {

	private static final int OFFERING_SIZE = 5;
	private static final String OFFERING_YEAR = "year";
	private Map<String, CourseOffering> courseOfferings;
	private CourseOfferingRepository courseOfferingRepository;
	private CourseOfferingRetriever offeringRetriever;

	@Before
	public void setUp() {
		createOfferings();
		offeringRetriever = mock(CourseOfferingRetriever.class);
		when(offeringRetriever.getOfferings()).thenReturn(courseOfferings);

		courseOfferingRepository = new CourseOfferingRepository();
		courseOfferingRepository.setOfferingRetriever(offeringRetriever);
	}

	@Test
	public void canGetAllCourseOfferings() {
		assertEquals(OFFERING_SIZE, courseOfferingRepository.getAll().size());
	}

	private void createOfferings() {
		courseOfferings = new HashMap<String, CourseOffering>();
		for (Integer i = 0; i < OFFERING_SIZE; i++) {
			courseOfferings.put(OFFERING_YEAR + i.toString(), mock(CourseOffering.class));
		}
	}

}
