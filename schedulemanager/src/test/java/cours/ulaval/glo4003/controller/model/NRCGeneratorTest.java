package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NRCGeneratorTest {

	@Test
	public void canGenerate() {
		String generatedNrc = NRCGenerator.generate();

		assertEquals(5, generatedNrc.length());
	}

}
