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
	private static final String COURSE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/bwckctlg.p_disp_course_detail?cat_term_in=201201&subj_code_in=GLO&crse_numb_in=1001";

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
		assertTrue(firstCourseLink.contains(COURSE_URL));
	}

	private Elements createElements() {
		Elements elements = new Elements();
		Element element = new Element(Tag.valueOf("a"), "");
		element.text("GLO-1001");
		elements.add(element);

		return elements;
	}
}
