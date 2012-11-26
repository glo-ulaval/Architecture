package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.TimeDedicated;

public class CourseParserTest {

	private static final int A_COURSE_CREDITS = 3;
	private static final String A_COURSE_ACRONYM = "GLO-2001";
	private static final String A_COURSE_TITLE = "Systèmes d'exploitation pour ingénieurs";
	private static final String A_COURSE_DESCRIPTION = "Historique et &eacute;volution. R&ocirc;les, composantes et fonctions d'un syst&egrave;me d'exploitation. Protection et performance d'un syst&egrave;me. Structures mat&eacute;rielles n&eacute;cessaires. Processus et allocation du CPU. Gestion de la m&eacute;moire et m&eacute;moire virtuelle. M&eacute;moire secondaire et cache. Gestion des entr&eacute;es/sorties. Syst&egrave;mes de fichiers. Coordination de processus et impasses. Programmation concurrente. &Eacute;tude de cas&nbsp;: UNIX, DOS, VMS, VM, WINDOWS.";
	private static final String A_COURSE_URL = "https://capsuleweb.ulaval.ca/pls/etprod7/bwckctlg.p_disp_course_detail?cat_term_in=201301&subj_code_in=GLO&crse_numb_in=2001";
	private List<String> prerequisitesAcronyms = new ArrayList<String>();

	private CourseParser courseParser;

	@Before
	public void setUp() throws Exception {
		courseParser = new CourseParser(A_COURSE_URL);
		generatePrerequisitesForCourse();
	}

	@Test
	public void canParseAcronym() {
		assertEquals(A_COURSE_ACRONYM, courseParser.getAcronym());
	}

	@Test
	public void canParseTitle() {
		assertEquals(A_COURSE_TITLE, courseParser.getTitle());
	}

	@Test
	public void canParseDescription() {
		assertEquals(A_COURSE_DESCRIPTION, courseParser.getDescription());
	}

	@Test
	public void canParseCredits() {
		assertEquals(A_COURSE_CREDITS, courseParser.getCredits());
	}

	@Test
	public void canParseCycle() {
		assertEquals(Cycle.Premier, courseParser.getCycle());
	}

	@Test
	public void canParseTimeDedicated() {
		TimeDedicated timeDedicated = new TimeDedicated(3, 2, 4);
		assertEquals(timeDedicated, courseParser.getTimeDedicated());
	}

	@Test
	public void canParsePrerequisites() {
		assertEquals(prerequisitesAcronyms, courseParser.getPrerequisites());
	}

	private void generatePrerequisitesForCourse() {
		prerequisitesAcronyms.add("IFT-1002");
		prerequisitesAcronyms.add("GIF-1001");
		prerequisitesAcronyms.add("IFT-2000");
		prerequisitesAcronyms.add("IFT-2900");
		prerequisitesAcronyms.add("GLO-2100");
		prerequisitesAcronyms.add("IFT-2008");
		prerequisitesAcronyms.add("GIF-1003");
	}
}
