package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class CourseBeanRetrieverUnitTest {

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
		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		retriever.setUnmarshaller(unmarshaller);

		assertEquals(unmarshaller, retriever.getUnmarshaller());
	}

	@Test
	public void canGetCourses() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		retriever.getCourses();
	}
}
