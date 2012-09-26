package cours.ulaval.glo4003.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

	private static final String CONFIG_FILE_PATH = "/Configs.txt";

	private static ConfigManager configManager;

	private Properties properties;

	private ConfigManager() {
		InputStream in = null;
		ResourcesLoader rs = new ResourcesLoader();

		try {
			in = rs.loadResource(String.class, CONFIG_FILE_PATH);
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
}
