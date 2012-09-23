package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.oxm.Unmarshaller;

public class CourseBeanRetrieverUnitTest {

	@Test
	public void canSetUnmarshallerInRetriever() {
		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		CourseBeanRetriever retriever = new CourseBeanRetriever();

		retriever.setUnmarshaller(unmarshaller);

		assertEquals(unmarshaller, retriever.getUnmarshaller());
	}
}
