package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.oxm.Marshaller;

public class CourseBeanRetrieverUnitTest {

	@Test
	public void canSetMarshallerInRetriever() {
		Marshaller marshaller = mock(Marshaller.class);
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		retriever.setMarshaller(marshaller);

		assertEquals(marshaller, retriever.getMarshaller());
	}
}
