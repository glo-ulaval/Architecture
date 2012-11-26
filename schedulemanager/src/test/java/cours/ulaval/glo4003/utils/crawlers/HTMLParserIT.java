package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class HTMLParserIT {

	private static final String A_SELECTOR = ".nttitle";
	private static final String A_COURSE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/bwckctlg.p_disp_course_detail?cat_term_in=201301&subj_code_in=GIF&crse_numb_in=1002";

	@Test
	public void canParseAnElement() throws Exception {
		HTMLLoader loader = new HTMLLoader();
		HTMLParser parser = new HTMLParser();

		Document document = loader.load(A_COURSE_URL);
		Elements elements = parser.parse(document, A_SELECTOR);

		assertTrue(elements.first().ownText().contains("Circuits logiques"));
	}
}
