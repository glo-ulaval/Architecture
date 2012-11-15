package cours.ulaval.glo4003.persistence;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.utils.ResourcesLoader;

public class XMLSerializer<T> {

	private Class<T> type;

	@Autowired
	@Qualifier("ResourcesLoader")
	private ResourcesLoader resourcesLoader;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public XMLSerializer(Class<T> type)
			throws JAXBException {
		this.type = type;
		JAXBContext context = JAXBContext.newInstance(type);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		unmarshaller = context.createUnmarshaller();
		unmarshaller.setAdapter(new ConflictXMLAdapter());
		resourcesLoader = new ResourcesLoader();
	}

	@SuppressWarnings("unchecked")
	public T deserialize(String resourceName)
			throws JAXBException,
				SerializationException {
		InputStream stream = resourcesLoader.loadResource(type, resourceName);

		if (stream != null) {
			return (T) unmarshaller.unmarshal(stream);
		} else {
			throw new SerializationException("Invalid resource name : " + resourceName);
		}
	}

	public void serialize(T element, String resourceName)
			throws JAXBException,
				URISyntaxException,
				FileNotFoundException {
		OutputStream stream = resourcesLoader.loadResourceForWriting(resourceName);

		marshaller.marshal(element, stream);
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setResourcesLoader(ResourcesLoader loader) {
		this.resourcesLoader = loader;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
