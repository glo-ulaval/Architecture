package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.SerializationException;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLSerializerTest {

	private final String A_RESOURCE_NAME = "A Resource Name";
	private ResourcesLoader loader;
	private XMLSerializer<CoursesXMLWrapper> serializer;

	@Before
	public void setUp() throws Exception {
		serializer = new XMLSerializer<CoursesXMLWrapper>(CoursesXMLWrapper.class);
		loader = mock(ResourcesLoader.class);
	}

	@Test
	public void canInstantiateXmlSerializer() throws Exception {
		assertNotNull(serializer);
	}

	@Test
	public void canDeserialize() throws Exception {
		InputStream stream = mock(InputStream.class);
		when(loader.loadResource(CoursesXMLWrapper.class, A_RESOURCE_NAME)).thenReturn(stream);
		serializer.setResourcesLoader(loader);

		CoursesXMLWrapper dto = mock(CoursesXMLWrapper.class);
		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		when(unmarshaller.unmarshal(stream)).thenReturn(dto);
		serializer.setUnmarshaller(unmarshaller);

		assertEquals(dto, serializer.deserialize(A_RESOURCE_NAME));
	}

	@Test(expected = SerializationException.class)
	public void cantDeserializeInvalidResource() throws Exception {
		when(loader.loadResource(CoursesXMLWrapper.class, A_RESOURCE_NAME)).thenReturn(null);
		serializer.setResourcesLoader(loader);

		serializer.deserialize(A_RESOURCE_NAME);
	}

	@Test()
	public void canSerialize() throws Exception {
		CoursesXMLWrapper dto = mock(CoursesXMLWrapper.class);
		Marshaller marshaller = mock(Marshaller.class);
		OutputStream stream = mock(OutputStream.class);
		when(loader.loadResourceForWriting(A_RESOURCE_NAME)).thenReturn(stream);
		serializer.setResourcesLoader(loader);
		serializer.setMarshaller(marshaller);

		serializer.serialize(dto, A_RESOURCE_NAME);

		verify(loader).loadResourceForWriting(A_RESOURCE_NAME);
		verify(marshaller).marshal(dto, stream);
	}
}
