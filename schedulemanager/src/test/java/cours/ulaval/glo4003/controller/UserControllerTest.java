package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.controller.model.UserModel;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;
import cours.ulaval.glo4003.utils.UserModelGenerator;

public class UserControllerTest {

	private static final String AN_IDUL = "VISEG";

	@Mock
	private UserRepository repository;
	@InjectMocks
	private UserController controller;

	private Principal principal;
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		principal = mock(Principal.class);
		user = mock(User.class);
		when(principal.getName()).thenReturn(AN_IDUL);
		when(repository.findByIdul(AN_IDUL)).thenReturn(user);
	}

	@Test
	public void canGetCreateUser() {
		ModelAndView mv = controller.getCreateUser();

		assertEquals("createuser", mv.getViewName());
	}

	@Test
	public void canCreateUser() throws Exception {
		UserModel model = UserModelGenerator.createUserModel();
		model.setRoles(Arrays.asList("Administrateur", "Enseignant"));
		ModelAndView mv = controller.createUser(model, principal);

		verify(repository).store(any(User.class));
		assertEquals("menu", mv.getViewName());
		assertEquals(user, mv.getModel().get("user"));
	}

	@Test
	public void canGetProfile() {
		ModelAndView mv = controller.getProfile(AN_IDUL);

		verify(repository).findByIdul(AN_IDUL);
		assertEquals("profile", mv.getViewName());
		assertTrue(mv.getModel().get("user") instanceof UserModel);
	}

	@Test
	public void canSaveProfile() throws Exception {
		UserModel model = UserModelGenerator.createUserModel();
		ModelAndView mv = controller.saveProfile(AN_IDUL, model, principal);

		verify(repository).store(any(User.class));
		assertEquals(user, mv.getModel().get("user"));
		assertEquals("menu", mv.getViewName());
	}
}
