package cours.ulaval.glo4003.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;

import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.repository.persistence.DAOException;

public class XMLSerializerTest {

	private XMLSerializer<CoursesPool> serializer;

	@Before
	public void setUp() throws Exception {
		serializer = new XMLSerializer<CoursesPool>(CoursesPool.class);
	}

	@Test
	public void canInstantiateXmlSerializer() throws Exception {
		assertNotNull(serializer);
	}

	@Test
	public void canDeserialize() throws Exception {
		String aResourceName = "A Resource Name";
		ResourcesLoader loader = mock(ResourcesLoader.class);
		InputStream stream = mock(InputStream.class);
		when(loader.loadResource(CoursesPool.class, aResourceName)).thenReturn(stream);
		serializer.setResourcesLoader(loader);

		CoursesPool pool = mock(CoursesPool.class);
		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		when(unmarshaller.unmarshal(stream)).thenReturn(pool);
		serializer.setUnmarshaller(unmarshaller);

		assertEquals(pool, serializer.deserialize(aResourceName));
	}

	@Test(expected = DAOException.class)
	public void cantDeserializeInvalidResource() throws Exception {
		String aResourceName = "A Resource Name";
		ResourcesLoader loader = mock(ResourcesLoader.class);
		when(loader.loadResource(CoursesPool.class, aResourceName)).thenReturn(null);
		serializer.setResourcesLoader(loader);

		serializer.deserialize(aResourceName);
	}
}
