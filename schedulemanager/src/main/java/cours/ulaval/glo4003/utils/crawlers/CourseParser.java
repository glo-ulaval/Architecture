package cours.ulaval.glo4003.utils.crawlers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.TimeDedicated;

public class CourseParser extends Parser {

	private static final String FULL_TITLE_SELECTOR = "table.datadisplaytable tbody tr td.nttitle";
	private static final String DESCRIPTION_SELECTOR = "table.datadisplaytable tbody tr td.ntdefault";

	public CourseParser(String uri) throws Exception {
		super(uri);
	}

	public Course getCourse() {
		return new Course();
	}

	public String getAcronym() {
		String fullTitle = htmlParser.parse(document, FULL_TITLE_SELECTOR).first().ownText();
		return fullTitle.split(" - ")[0].replace(" ", "-");
	}

	public String getTitle() {
		String fullTitle = htmlParser.parse(document, FULL_TITLE_SELECTOR).first().ownText();
		return fullTitle.split(" - ")[1];
	}

	public String getDescription() {
		String text = htmlParser.parse(document, DESCRIPTION_SELECTOR).first().html();
		return text.split(" <br /> ")[0];
	}

	public int getCredits() {
		String text = htmlParser.parse(document, DESCRIPTION_SELECTOR).first().html();
		return Integer.valueOf(text.split(" <br /> ")[1].split(",")[0]);
	}

	public Cycle getCycle() {
		String text = htmlParser.parse(document, DESCRIPTION_SELECTOR).first().html();
		String cycle = text.split(" <br /> ")[5].split(" </span> ")[1].replace(" cycle", "");
		return Cycle.valueOf(cycle);
	}

	public TimeDedicated getTimeDedicated() {
		String text = htmlParser.parse(document, DESCRIPTION_SELECTOR).first().html();
		int courseHours = Integer.valueOf(text.split(" <br /> ")[2].split(",")[0]);
		int laboHours = Integer.valueOf(text.split(" <br /> ")[3].split(",")[0]);
		int otherHours = Integer.valueOf(text.split(" <br /> ")[4].split(",")[0]);

		return new TimeDedicated(courseHours, laboHours, otherHours);
	}

	public List<String> getPrerequisites() {
		List<String> prerequisitesAcronyms = new ArrayList<String>();

		Elements elements = htmlParser.parse(document, DESCRIPTION_SELECTOR);
		elements = elements.select("a");
		for (Element element : elements) {
			if (!element.ownText().contains("Régulier") && !element.ownText().contains("Retour à la page précédente")
					&& !element.ownText().contains("Nouvelle recherche")) {
				prerequisitesAcronyms.add(element.ownText().replace(" ", "-"));
			}
		}
		return prerequisitesAcronyms;
	}
}
