package cours.ulaval.glo4003.utils.crawlers;

import org.jsoup.nodes.Document;

public abstract class Parser {

	protected HTMLLoader htmlLoader = new HTMLLoader();
	protected HTMLParser htmlParser = new HTMLParser();

	protected Document document;

	public Parser() {

	}

	public Parser(String uri) throws Exception {
		document = htmlLoader.load(uri);
	}
}
