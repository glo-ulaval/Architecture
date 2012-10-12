package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;

public class LoginControllerTest {

	private static final String UN_IDUL = "unIdul";

	@Mock
	private ModelMap model;

	@Mock
	private Principal principal;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpSession session;

	@Mock
	private User user;

	@Mock
	private UserRepository repository;

	@InjectMocks
	private LoginController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(request.getSession()).thenReturn(session);
	}

	@Test
	public void canGetMenu() {
		when(principal.getName()).thenReturn(UN_IDUL);
		when(repository.findByIdul(principal.getName())).thenReturn(user);

		assertEquals("menu", controller.menu(model, principal, request));

		verify(session).setAttribute("user", user);
	}

	@Test
	public void canGetLoginFailedPage() {
		ModelAndView mv = controller.loginFailed(model);

		assertTrue(mv.getModel().containsKey("loginError"));
	}

	@Test
	public void canLogout() {

		ModelAndView mv = controller.logout(model, request);

		verify(session).invalidate();

		assertEquals("home", mv.getViewName());
	}
}
