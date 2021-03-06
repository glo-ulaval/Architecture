package cours.ulaval.glo4003.utils;

import java.io.IOException;
import java.util.Properties;

public final class ConfigManager {

	private static String configFilePath = "/Configs.properties";
	private static ConfigManager configManager;

	private Properties properties;

	private ConfigManager() {
		try {
			properties = new Properties();
			properties.load(ConfigManager.class.getResourceAsStream(configFilePath));
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

	public String getProgramSheetFilePath() {
		return properties.getProperty("ProgramSheetFilePath");
	}

	public String getSMTPServerHost() {
		return properties.getProperty("STMP.Server.host");
	}

	public Integer getSMTPServerPort() {
		return Integer.decode(properties.getProperty("STMP.Server.port"));
	}

	public String getSMTPServerUsername() {
		return properties.getProperty("STMP.Server.username");
	}

	public String getSMTPServerPassword() {
		return properties.getProperty("STMP.Server.password");
	}

	public String getSMTPServerProtocol() {
		return properties.getProperty("STMP.Server.protocol");
	}

	// Use for tests
	public static void setConfigFilePath(String path) {
		configFilePath = path;
	}
}