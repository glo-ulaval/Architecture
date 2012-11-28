package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;

public class CourseParserTest {

	private HTMLParser htmlParser = mock(HTMLParser.class);
	private HTMLLoader htmlLoader = mock(HTMLLoader.class);
	private CourseParser parser;
	private Element element;
	private Elements elements;

	@Before
	public void setUp() throws Exception {
		elements = mock(Elements.class);
		element = new Element(Tag.valueOf("div"), "");
		when(htmlLoader.load(anyString())).thenReturn(mock(Document.class));
		when(htmlParser.parse(any(Document.class), anyString())).thenReturn(elements);
		parser = new CourseParser(htmlLoader, htmlParser);
	}

	@Test
	public void canParseAcronym() {
		element = element.text("GLO 2001 - Systèmes d'exploitation pour ingénieurs");
		when(elements.first()).thenReturn(element);

		parser.addAcronym();
		Course course = parser.getCourse();

		assertEquals("GLO-2001", course.getAcronym());
	}

	@Test
	public void canParseTitle() {
		element = element.text("GLO 2001 - Systèmes d'exploitation pour ingénieurs");
		when(elements.first()).thenReturn(element);

		parser.addTitle();
		Course course = parser.getCourse();

		assertEquals("Systèmes d'exploitation pour ingénieurs", course.getTitle());
	}
}
