package cours.ulaval.glo4003.utils.crawlers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLParser {

	public Elements parse(Document document, String selector) {
		return document.select(selector);
	}
}
