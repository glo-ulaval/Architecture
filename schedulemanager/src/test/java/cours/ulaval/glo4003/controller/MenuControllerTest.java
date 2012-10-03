package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MenuControllerTest {

	@Test
	public void canGetMenu() {
		MenuController controller = new MenuController();

		assertEquals("menu", controller.menu());
	}
}
