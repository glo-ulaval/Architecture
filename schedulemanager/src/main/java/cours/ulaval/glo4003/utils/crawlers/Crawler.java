package cours.ulaval.glo4003.utils.crawlers;

import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class Crawler {

	private final static String GLO_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/y_bwckprog.p_afficher_fiche?p_session=200909&p_code_prog=B-GLO&p_code_majr=GLO&p_code_camp=";
	private final static String IFT_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/y_bwckprog.p_afficher_fiche?p_type_index=4&p_code_prog=B-IFT&p_code_majr=IFT&p_code_camp=";

	@Inject
	private CourseRepository courseRepository;

	private CourseParserFactory courseParserFactory;
	private ProgramParser programParserGlo;
	private ProgramParser programParserIft;

	public Crawler() throws Exception {
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
