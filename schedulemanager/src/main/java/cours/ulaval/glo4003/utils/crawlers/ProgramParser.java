package cours.ulaval.glo4003.utils.crawlers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProgramParser extends Parser {

	private static final String GLO_ACRONYM = "GLO";
	private static final String IFT_ACRONYM = "IFT";
	private static final String BASE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/bwckctlg.p_disp_course_detail?cat_term_in=201201&subj_code_in=PROGRAM&crse_numb_in=NUMBER";
	private static final String COURSE_URL_SELECTOR = "ul.cours li span.col1";

	public ProgramParser(String uri) throws Exception {
		super(uri);
	}

	public List<String> getCoursesList() {
		List<String> coursesList = new ArrayList<String>();
		Elements elements = htmlParser.parse(document, COURSE_URL_SELECTOR);

		for (Element element : elements) {
			String acronym = element.ownText();
			String[] splitAcronym = acronym.split("-");
			if (splitAcronym.length == 2 && (splitAcronym[0].equals(GLO_ACRONYM) || splitAcronym[0].equals(IFT_ACRONYM))) {
				coursesList.add(BASE_URL.replace("PROGRAM", splitAcronym[0]).replace("NUMBER", splitAcronym[1]));
			}
		}

		return coursesList;
	}

	// For tests only
	public ProgramParser(HTMLLoader loader, HTMLParser parser) {
		htmlLoader = loader;
		htmlParser = parser;
	}

}
