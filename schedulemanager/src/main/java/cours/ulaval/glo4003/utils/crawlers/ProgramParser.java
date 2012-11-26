package cours.ulaval.glo4003.utils.crawlers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProgramParser extends Parser {

	public static final String BASE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/";
	private static final String COURSE_URL_SELECTOR = "div.texte_contenu_exig table tr td.dddefault a";

	public ProgramParser(String uri) throws Exception {
		super(uri);
	}

	public List<String> getCoursesList() {
		List<String> coursesList = new ArrayList<String>();
		Elements elements = htmlParser.parse(document, COURSE_URL_SELECTOR);

		for (Element element : elements) {
			coursesList.add(BASE_URL + element.attr("href"));
		}

		return coursesList;
	}

	// For tests only
	public ProgramParser(HTMLLoader loader, HTMLParser parser) {
		htmlLoader = loader;
		htmlParser = parser;
	}

}
