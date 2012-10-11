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

	private static final String JSON_STRING = "{\"monday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"tuesday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"wednesday\":[true,true,true,true,true,true,true,true,true,true,false,false,false],\"thursday\":[false,false,false,false,false,true,true,true,true,true,true,true,true],\"friday\":[true,true,true,true,true,true,true,true,true,true,false,false,false]}";

	@Mock
	ObjectMapper mapper;

	@Mock
	AvailabilityModel availabilityModel;

	@InjectMocks
	Availability availabilities;

	@Before
	public void setUp()
			throws Exception {
		MockitoAnnotations.initMocks(this);
		when(mapper.readValue(JSON_STRING, AvailabilityModel.class)).thenReturn(availabilityModel);
	}

	@Test
	public void canInstantiateAnAvailability() {
		assertNotNull(availabilities);
	}

}
