package cours.ulaval.glo4003.utils.crawlers;

public class CourseParserFactory {

	public CourseParser getCourseParser(String uri) throws Exception {
		return new CourseParser(uri);
	}

}
