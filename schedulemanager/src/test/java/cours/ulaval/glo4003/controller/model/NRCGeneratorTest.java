package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NRCGeneratorTest {

	@Test
	public void canGenerate() {
		Integer generatedNrc = NRCGenerator.generate();

		assertEquals(5, String.valueOf(generatedNrc).length());
	}

}
