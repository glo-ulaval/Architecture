package cours.ulaval.glo4003.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;

public class ResourcesLoaderTest {

	private ResourcesLoader loader;

	@Before
	public void setUp() {
		loader = new ResourcesLoader();
	}

	@Test
	public void canLoadExistingResource() {
		assertNotNull(loader.loadResource(CoursesPool.class, ResourcesPaths.COURSES_FILE));
	}

	@Test
	public void cantLoadUnexistingResource() {
		assertNull(loader.loadResource(CoursesPool.class, "This is an unexisting resource"));
	}
}
