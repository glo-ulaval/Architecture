package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class ProgramParserTest {

	private HTMLParser htmlParser = mock(HTMLParser.class);
	private HTMLLoader htmlLoader = mock(HTMLLoader.class);
	private ProgramParser parser;

	@Before
	public void setUp() throws Exception {
		when(htmlLoader.load(anyString())).thenReturn(mock(Document.class));
		when(htmlParser.parse(any(Document.class), anyString())).thenReturn(createElements());
		parser = new ProgramParser(htmlLoader, htmlParser);
	}

	@Test
	public void canGetCoursesList() throws Exception {
		List<String> coursesList = parser.getCoursesList();
		String firstCourseLink = coursesList.get(0);

		assertEquals(1, coursesList.size());
		assertTrue(firstCourseLink.contains(ProgramParser.BASE_URL));
	}

	private Elements createElements() {
		Elements elements = new Elements();
		Element element = new Element(Tag.valueOf("a"), "");
		elements.add(element);

		return elements;
	}
}
