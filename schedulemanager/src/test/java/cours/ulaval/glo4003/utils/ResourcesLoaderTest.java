package cours.ulaval.glo4003.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.persistence.CoursesDTO;

public class ResourcesLoaderTest {

	private final String AN_UNEXISTING_RESOURCE_NAME = "An unexisting resource";
	private ResourcesLoader loader;

	@Before
	public void setUp() {
		loader = new ResourcesLoader();
	}

	@Test
	public void canLoadExistingResource() {
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		assertNotNull(loader.loadResource(CoursesDTO.class, resourcesPaths.getCoursesFilePath()));
	}

	@Test
	public void cantLoadUnexistingResource() {
		assertNull(loader.loadResource(CoursesDTO.class, AN_UNEXISTING_RESOURCE_NAME));
	}

	@Test(expected = FileNotFoundException.class)
	public void cantLoadUnexistingResourceForWriting() throws Exception {
		loader.loadResourceForWriting(AN_UNEXISTING_RESOURCE_NAME);
	}
}
