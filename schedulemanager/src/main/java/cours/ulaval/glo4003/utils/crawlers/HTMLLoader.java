package cours.ulaval.glo4003.utils.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLLoader {

	public Document load(String uri) throws Exception {
		return Jsoup.connect(uri).get();
	}
}
