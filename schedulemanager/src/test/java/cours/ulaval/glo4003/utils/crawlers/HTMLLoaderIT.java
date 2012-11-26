package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class HTMLLoaderIT {

	private final static String AN_URL = "http://www.google.com";

	@Test
	public void canLoadHTML() throws Exception {
		HTMLLoader loader = new HTMLLoader();

		Document document = loader.load(AN_URL);

		assertNotNull(document);
		assertEquals("Google", document.title());
	}

}
