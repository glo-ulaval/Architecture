package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.repository.UserRepository;

public class UserControllerTest {

	@Mock
	private UserRepository repository;
	@InjectMocks
	private UserController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canGetCreateUser() {
		ModelAndView mv = controller.getCreateUser();

		assertEquals("createuser", mv.getViewName());
	}
}
