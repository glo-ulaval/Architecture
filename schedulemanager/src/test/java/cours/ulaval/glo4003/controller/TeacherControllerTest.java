package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class TeacherControllerTest {

	private static final String JSON_STRING = "{\"monday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"tuesday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"wednesday\":[true,true,true,true,true,true,true,true,true,true,false,false,false],\"thursday\":[false,false,false,false,false,true,true,true,true,true,true,true,true],\"friday\":[true,true,true,true,true,true,true,true,true,true,false,false,false]}";

	@Mock
	private AvailabilityModel availabilityModel;

	@Mock
	private Availability availability;

	@Mock
	private ObjectMapper mapper;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpSession session;

	@Mock
	private AvailabilityRepository repository;

	@Mock
	private User user;

	@InjectMocks
	private TeacherController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(mapper.readValue(JSON_STRING, AvailabilityModel.class)).thenReturn(availabilityModel);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("user")).thenReturn(user);
		when(user.getIdul()).thenReturn("enseignant");

		when(repository.findByIdul(user.getIdul())).thenReturn(availability);

		when(mapper.writeValueAsString(availabilityModel)).thenReturn(JSON_STRING);
	}

	@Test
	public void canGetAvailibilitiesView() {

		ModelAndView mv = controller.availabilities(request);

		assertEquals("availabilities", mv.getViewName());
		assertTrue(mv.getModel().containsKey("availabilitiesJSON"));
	}

	@Test
	public void canGetAvailibilitiesEditView() throws Exception {
		assertEquals("Vos disponibilités ont été enregistrées avec succès", controller.availabilities(JSON_STRING, request));
	}
}
