package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class TeacherControllerTest {

	private static final String JSON_STRING = "{\"monday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"tuesday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"wednesday\":[true,true,true,true,true,true,true,true,true,true,false,false,false],\"thursday\":[false,false,false,false,false,true,true,true,true,true,true,true,true],\"friday\":[true,true,true,true,true,true,true,true,true,true,false,false,false]}";

	@Mock
	private AvailabilityModel availability;

	@Mock
	private ObjectMapper mapper;

	@InjectMocks
	private TeacherController controller;

	@Before
	public void setUp()
			throws Exception {
		MockitoAnnotations.initMocks(this);

		when(mapper.readValue(JSON_STRING, AvailabilityModel.class)).thenReturn(availability);
	}

	@Test
	public void canGetAvailibilitiesView() {
		assertEquals("availabilities", controller.availabilities());
	}

	@Test
	public void canGetAvailibilitiesEditView() {
		assertEquals("Vos disponibilités ont été enregistrés avec succès", controller.availabilities(JSON_STRING));
	}
}
