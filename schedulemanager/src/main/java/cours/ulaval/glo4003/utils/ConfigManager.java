package cours.ulaval.glo4003.utils;

import java.io.IOException;
import java.util.Properties;

public final class ConfigManager {

	private static final String CONFIG_FILE_PATH = "/Configs.properties";

	private static ConfigManager configManager;

	private Properties properties;

	private ConfigManager() {
		try {
			properties = new Properties();
			properties.load(ConfigManager.class
					.getResourceAsStream(CONFIG_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ConfigManager getConfigManager() {
		if (configManager == null) {
			configManager = new ConfigManager();
		}
		return configManager;
	}

	public String getCoursesFilePath() {
		return properties.getProperty("CoursesFilePath");
	}

	public String getOfferingsFilePath() {
		return properties.getProperty("OfferingsFilePath");
	}
}
