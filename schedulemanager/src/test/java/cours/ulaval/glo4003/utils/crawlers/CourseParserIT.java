package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.TimeDedicated;

public class CourseParserIT {

	private static final int A_COURSE_CREDITS = 3;
	private static final String A_COURSE_ACRONYM = "GLO-2001";
	private static final String A_COURSE_TITLE = "Systèmes d'exploitation pour ingénieurs";
	private static final String A_COURSE_DESCRIPTION = "Historique et &eacute;volution. R&ocirc;les, composantes et fonctions d'un syst&egrave;me d'exploitation. Protection et performance d'un syst&egrave;me. Structures mat&eacute;rielles n&eacute;cessaires. Processus et allocation du CPU. Gestion de la m&eacute;moire et m&eacute;moire virtuelle. M&eacute;moire secondaire et cache. Gestion des entr&eacute;es/sorties. Syst&egrave;mes de fichiers. Coordination de processus et impasses. Programmation concurrente. &Eacute;tude de cas&nbsp;: UNIX, DOS, VMS, VM, WINDOWS.";
	private static final String A_COURSE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/bwckctlg.p_disp_course_detail?cat_term_in=201301&subj_code_in=GLO&crse_numb_in=2001";
	private static final String A_SECOND_PREREQUISITE_ACRONYM = "GIF-1001";
	private static final String A_FIRST_PREREQUISITE_ACRONYM = "IFT-1002";

	private CourseParser courseParser;

	@Before
	public void setUp() throws Exception {
		courseParser = new CourseParser(A_COURSE_URL);
	}

	@Test
	public void canParseAcronym() {
		courseParser.addAcronym();
		Course course = courseParser.getCourse();
		assertEquals(A_COURSE_ACRONYM, course.getAcronym());
	}

	@Test
	public void canParseTitle() {
		courseParser.addTitle();
		Course course = courseParser.getCourse();
		assertEquals(A_COURSE_TITLE, course.getTitle());
	}

	@Test
	public void canParseDescription() {
		courseParser.addDescription();
		Course course = courseParser.getCourse();
		assertEquals(A_COURSE_DESCRIPTION, course.getDescription());
	}

	@Test
	public void canParseCredits() {
		courseParser.addCredits();
		Course course = courseParser.getCourse();
		assertEquals(A_COURSE_CREDITS, course.getCredits());
	}

	@Test
	public void canParseCycle() {
		courseParser.addCycle();
		Course course = courseParser.getCourse();
		assertEquals(Cycle.Premier, course.getCycle());
	}

	@Test
	public void canParseTimeDedicated() {
		courseParser.addTimeDedicated();
		Course course = courseParser.getCourse();
		TimeDedicated timeDedicated = new TimeDedicated(3, 2, 4);
		assertEquals(timeDedicated, course.getTimeDedicated());
	}

	@Test
	public void canParsePrerequisites() {
		courseParser.addPrerequisites();
		Course course = courseParser.getCourse();
		List<Prerequisite> prerequisites = course.getPrerequisites();

		assertEquals(2, prerequisites.size());

		Prerequisite prerequisite = prerequisites.get(0);
		List<String> acronyms = prerequisite.getAcronyms();

		assertTrue(acronyms.contains(A_FIRST_PREREQUISITE_ACRONYM));
		assertTrue(acronyms.contains(A_SECOND_PREREQUISITE_ACRONYM));
	}
}
