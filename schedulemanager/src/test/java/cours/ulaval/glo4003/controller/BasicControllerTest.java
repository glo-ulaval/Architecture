package cours.ulaval.glo4003.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasicControllerTest {

	@Test
	public void canGetHome() {
		BasicController controller = new BasicController();

		assertEquals("home", controller.home());
	}
}
