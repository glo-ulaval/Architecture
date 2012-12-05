package cours.ulaval.glo4003.utils.crawlers;

import java.util.List;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class Crawler {

	private final static String GLO_URL = "http://www2.ulaval.ca/les-etudes/programmes/repertoire/details/baccalaureat-en-genie-logiciel-b-ing.html";
	private final static String IFT_URL = "http://www2.ulaval.ca/les-etudes/programmes/repertoire/details/baccalaureat-en-informatique-b-sc-a.html";

	private CourseRepository courseRepository;

	private CourseParserFactory courseParserFactory;
	private ProgramParser programParserGlo;
	private ProgramParser programParserIft;

	public Crawler(CourseRepository courseRepository) throws Exception {
		this.courseRepository = courseRepository;
		courseRepository.clear();
		programParserGlo = new ProgramParser(GLO_URL);
		programParserIft = new ProgramParser(IFT_URL);
		courseParserFactory = new CourseParserFactory();
	}

	public void crawl() throws Exception {
		crawlWithParser(programParserGlo);
		crawlWithParser(programParserIft);
	}

	private void crawlWithParser(ProgramParser parser) throws Exception {
		List<String> courses = parser.getCoursesList();
		courses.remove(courses.size() - 1);
		for (String courseUri : courses) {
			CourseParser courseParser = courseParserFactory.getCourseParser(courseUri);
			if (courseParser.courseShouldBeAddedToCourseList()) {
				Course course = courseParser.getCompleteCourse();
				courseRepository.store(course);
			}
		}
	}

	// Use for tests only
	public Crawler(CourseRepository courseRepository, ProgramParser gloProgramParser, ProgramParser iftProgramParser,
			CourseParserFactory courseFactory) {
		this.courseRepository = courseRepository;
		this.courseParserFactory = courseFactory;
		this.programParserGlo = gloProgramParser;
		this.programParserIft = iftProgramParser;
	}
}
