package cours.ulaval.glo4003.persistence;

import java.io.InputStream;

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

	public XMLSerializer(Class<T> type) throws JAXBException {
		this.type = type;
		JAXBContext context = JAXBContext.newInstance(type);
		marshaller = context.createMarshaller();
		unmarshaller = context.createUnmarshaller();
		resourcesLoader = new ResourcesLoader();
	}

	@SuppressWarnings("unchecked")
	public T deserialize(String resourceName) throws JAXBException, SerializationException {
		InputStream stream = resourcesLoader.loadResource(type, resourceName);

		if (stream != null) {
			return (T) unmarshaller.unmarshal(stream);
		} else {
			throw new SerializationException("Invalid resource name : " + resourceName);
		}
	}

	public void setResourcesLoader(ResourcesLoader loader) {
		this.resourcesLoader = loader;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
