package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class HTMLParserTest {

	@Test
	public void canParseHTML() {
		Elements elements = new Elements();
		Document document = mock(Document.class);
		when(document.select(anyString())).thenReturn(elements);

		HTMLParser parser = new HTMLParser();
		Elements parsedElements = parser.parse(document, ".nttitle");

		assertEquals(elements, parsedElements);
		verify(document).select(anyString());
	}
}
