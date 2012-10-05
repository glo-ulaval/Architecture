package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

public class MenuControllerTest {

	@Mock
	private ModelMap model;

	@Mock
	private Principal principal;

	@InjectMocks
	private MenuController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canGetMenu() {
		assertEquals("menu", controller.menu(model, principal));
	}
}
