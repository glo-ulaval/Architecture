package cours.ulaval.glo4003.repository.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;

import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ResourcesLoader;
import cours.ulaval.glo4003.utils.ResourcesPaths;

public class CourseBeanRetrieverTest {

	private Unmarshaller unmarshaller;

	@Before
	public void setUp() {
		unmarshaller = mock(Unmarshaller.class);
	}

	@Test
	public void canInstantiateRetriever() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		assertNotNull(retriever);
	}

	@Test
	public void canGetUnmarshaller() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		assertNotNull(retriever.getUnmarshaller());
	}

	@Test
	public void canSetUnmarshallerInRetriever() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		retriever.setUnmarshaller(unmarshaller);

		assertEquals(unmarshaller, retriever.getUnmarshaller());
	}

	@Test
	public void canGetCourses() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();
		CoursesPool pool = mock(CoursesPool.class);
		ResourcesLoader loader = mock(ResourcesLoader.class);
		InputStream stream = mock(InputStream.class);
		when(loader.loadResource(CourseBeanRetriever.class, ResourcesPaths.COURSES_FILE)).thenReturn(stream);
		when(unmarshaller.unmarshal(stream)).thenReturn(pool);

		retriever.setUnmarshaller(unmarshaller);
		retriever.setResourcesLoader(loader);

		assertEquals(pool, retriever.getCourses());
	}
}