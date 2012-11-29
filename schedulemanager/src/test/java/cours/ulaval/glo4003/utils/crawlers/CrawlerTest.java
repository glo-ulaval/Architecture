package cours.ulaval.glo4003.utils.crawlers;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class CrawlerTest {

	private static final String A_GLO_ACRONYM = "GLO-1001";
	private static final String AN_IFT_ACRONYM = "GLO-1001";

	private CourseParser courseParser;
	private CourseRepository repository;
	private ProgramParser gloProgramParser;
	private ProgramParser iftProgramParser;
	private CourseParserFactory factory;

	@Before
	public void setUp() throws Exception {
		courseParser = mock(CourseParser.class);

		repository = mock(CourseRepository.class);
		gloProgramParser = mock(ProgramParser.class);
		iftProgramParser = mock(ProgramParser.class);
		factory = mock(CourseParserFactory.class);

		when(gloProgramParser.getCoursesList()).thenReturn(new ArrayList<String>(Arrays.asList(A_GLO_ACRONYM, A_GLO_ACRONYM)));
		when(iftProgramParser.getCoursesList()).thenReturn(new ArrayList<String>(Arrays.asList(AN_IFT_ACRONYM, AN_IFT_ACRONYM)));
		when(courseParser.getCompleteCourse()).thenReturn(mock(Course.class));
		when(factory.getCourseParser(anyString())).thenReturn(courseParser);
	}

	@Test
	public void canCrawlWithCoursesThatShouldBeAdded() throws Exception {
		when(courseParser.courseShouldBeAddedToCourseList()).thenReturn(true);
		Crawler crawler = new Crawler(repository, gloProgramParser, iftProgramParser, factory);

		crawler.crawl();

		verify(repository, times(2)).store(any(Course.class));
	}

	@Test
	public void canCrawlWithCoursesThatShouldNotBeAdded() throws Exception {
		when(courseParser.courseShouldBeAddedToCourseList()).thenReturn(false);
		Crawler crawler = new Crawler(repository, gloProgramParser, iftProgramParser, factory);

		crawler.crawl();

		verify(repository, times(0)).store(any(Course.class));
	}
}
