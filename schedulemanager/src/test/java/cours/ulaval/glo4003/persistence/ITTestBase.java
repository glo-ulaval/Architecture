package cours.ulaval.glo4003.persistence;

import org.junit.BeforeClass;

import cours.ulaval.glo4003.utils.ConfigManager;

public class ITTestBase {

	@BeforeClass
	public static void init() {
		ConfigManager.setConfigFilePath("/TestConfigs.properties");
	}

}
