package cours.ulaval.glo4003.utils;

import java.io.*;
import java.util.*;

public final class ConfigManager {

	private static String CONFIG_FILE_PATH = "/Configs.properties";

	private static ConfigManager configManager;

	private Properties properties;

	private ConfigManager() {
		try {
			properties = new Properties();
			properties.load(ConfigManager.class.getResourceAsStream(CONFIG_FILE_PATH));
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

	public String getUsersFilePath() {
		return properties.getProperty("UsersFilePath");
	}

	public String getOfferingsFilePath() {
		return properties.getProperty("OfferingsFilePath");
	}

	public String getSchedulesFilepath() {
		return properties.getProperty("SchedulesFilePath");
	}

	public String getAvailabilitiesFilePath() {
		return properties.getProperty("AvailabilitiesFilePath");
	}

	// Use for tests
	public static void setConfigFilePath(String path) {
		CONFIG_FILE_PATH = path;
	}
}