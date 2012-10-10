package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class LoginControllerTest {

	@Mock
	private ModelMap model;

	@Mock
	private Principal principal;

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private LoginController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canGetMenu() {
		// TODO-FIX
		// assertEquals("menu", controller.menu(model, principal, request));
	}

	@Test
	public void canGetLoginFailedPage() {
		ModelAndView mv = controller.loginFailed(model);

		assertTrue(mv.getModel().containsKey("loginError"));
	}

	@Test
	public void canLogout() {
		ModelAndView mv = controller.logout(model);

		assertEquals("home", mv.getViewName());
	}
}
