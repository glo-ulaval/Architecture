package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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

	@Test
	public void canCreateUser() throws Exception {
		UserModel model = UserModelGenerator.createUserModel();
		model.setRoles(Arrays.asList("Administrateur", "Enseignant"));
		ModelAndView mv = controller.createUser(model);

		verify(repository).store(any(User.class));
		assertEquals("menu", mv.getViewName());
	}
}
