package cours.ulaval.glo4003.controller.model;

import java.util.Random;

public final class NRCGenerator {

	private final static Integer INTEGER_BASE = 10000;

	public static Integer generate() {
		Random random = new Random();
		Integer generatedNrc = random.nextInt(89999);

		return generatedNrc + INTEGER_BASE;
	}
}
