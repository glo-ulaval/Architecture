package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class AvailabilityTest {

	private static final String JSON_STRING = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,2,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";

	@Mock
	ObjectMapper mapper;

	@Mock
	AvailabilityModel availabilityModel;

	@InjectMocks
	Availability availabilities;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(mapper.readValue(JSON_STRING, AvailabilityModel.class)).thenReturn(availabilityModel);
	}

	@Test
	public void canInstantiateAnAvailability() {
		assertNotNull(availabilities);
	}

}
