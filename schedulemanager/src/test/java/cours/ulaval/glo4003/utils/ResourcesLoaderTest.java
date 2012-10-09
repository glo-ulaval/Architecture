package cours.ulaval.glo4003.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.persistence.CoursesXMLWrapper;

public class ResourcesLoaderTest {

	private final String A_TEST_RESOURCE = "/TestResource.txt";
	private final String AN_UNEXISTING_RESOURCE_NAME = "An unexisting resource";
	private ResourcesLoader loader;

	@Before
	public void setUp() {
		loader = new ResourcesLoader();
	}

	@Test
	public void canLoadExistingResource() {
		ConfigManager resourcesPaths = ConfigManager.getConfigManager();
		assertNotNull(loader.loadResource(CoursesXMLWrapper.class, resourcesPaths.getCoursesFilePath()));
	}

	@Test
	public void canLoadExistingResourceForWriting() throws Exception {
		assertNotNull(loader.loadResourceForWriting(A_TEST_RESOURCE));
	}

	@Test
	public void cantLoadUnexistingResource() {
		assertNull(loader.loadResource(CoursesXMLWrapper.class, AN_UNEXISTING_RESOURCE_NAME));
	}

	@Test(expected = FileNotFoundException.class)
	public void cantLoadUnexistingResourceForWriting() throws Exception {
		loader.loadResourceForWriting(AN_UNEXISTING_RESOURCE_NAME);
	}
}
