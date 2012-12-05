package cours.ulaval.glo4003.utils.crawlers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Cycle;
import cours.ulaval.glo4003.domain.TimeDedicated;

public class CourseParserTest {

	private static final String A_GLO_TITLE = "GLO 2001 - Systèmes d'exploitation pour ingénieurs";
	private static final String A_COMPLETE_DESCRIPTION = "This is description. <br /> 3,000 Cr&eacute;dits inscrits <br /> 3,000 Heures de cours <br /> 2,000 Heures de labo <br /> 4,000 Autres heures <br /> <br /> <span class=\"fieldlabeltext\">Cycle(s): </span> Premier cycle <br /> <span class=\"fieldlabeltext\">Mode d'enseignement: </span> <a href=\"/pls/etprod7/bwckctlg.p_disp_listcrse?term_in=201301&amp;subj_in=GLO&amp;crse_in=2001&amp;schd_in=R\">R&eacute;gulier</a> <br /> <span class=\"fieldlabeltext\">Prochaine pr&eacute;sentation anticip&eacute;e : </span> Non d&eacute;termin&eacute; <br /> <br /> Facult&eacute;: Sciences et g&eacute;nie <br /> D&eacute;partement: Informatique et g&eacute;nie logiciel <br /> <br /> <span class=\"fieldlabeltext\">Information sur le cours:</span> <br /> Famille d&eacute;r&eacute;glement&eacute;e <br /> <br /> <span class=\"fieldlabeltext\">Restrictions:</span> <br /> Ne peut pas &ecirc;tre inscrit &agrave; l'un des cycles suivants:&nbsp; &nbsp; &nbsp; <br /> &nbsp; &nbsp; &nbsp; &Eacute;ducation continue <br /> Doit &ecirc;tre inscrit &agrave; l'une des mati&egrave;res principales suivantes:&nbsp; &nbsp; &nbsp; <br /> &nbsp; &nbsp; &nbsp; G&eacute;nie logiciel <br /> <br /> <span class=\"fieldlabeltext\">Ancienne r&eacute;f&eacute;rence de cours:</span> GLO 22878 <br /> <br /> <span class=\"fieldlabeltext\">Substitutions permanentes:</span> <br /> Aucune <br /> <br /> <span class=\"fieldlabeltext\">Pr&eacute;alables:</span> <br /> (<a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=IFT&amp;sel_subj=&amp;sel_crse_strt=1002&amp;sel_crse_end=1002&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">IFT 1002</a> <b>OU</b> <a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=GIF&amp;sel_subj=&amp;sel_crse_strt=1001&amp;sel_crse_end=1001&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">GIF 1001</a>) <b>ET</b> (<a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=IFT&amp;sel_subj=&amp;sel_crse_strt=2000&amp;sel_crse_end=2000&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">IFT 2000</a> <b>OU</b> <a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=IFT&amp;sel_subj=&amp;sel_crse_strt=2900&amp;sel_crse_end=2900&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">IFT 2900</a> <b>OU</b> <a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=GLO&amp;sel_subj=&amp;sel_crse_strt=2100&amp;sel_crse_end=2100&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">GLO 2100</a> <b>OU</b> <a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=IFT&amp;sel_subj=&amp;sel_crse_strt=2008&amp;sel_crse_end=2008&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">IFT 2008</a> <b>OU</b> <a href=\"bwckctlg.p_display_courses?term_in=201301&amp;one_subj=GIF&amp;sel_subj=&amp;sel_crse_strt=1003&amp;sel_crse_end=1003&amp;sel_levl=&amp;sel_schd=&amp;sel_coll=&amp;sel_divs=&amp;sel_dept=&amp;sel_attr=\">GIF 1003</a>) <br /> <br /> <br />";
	private HTMLParser htmlParser = mock(HTMLParser.class);
	private HTMLLoader htmlLoader = mock(HTMLLoader.class);
	private CourseParser parser;
	private Element element;
	private Elements elements;

	@Before
	public void setUp() throws Exception {
		elements = mock(Elements.class);
		element = new Element(Tag.valueOf("a"), "");
		when(htmlLoader.load(anyString())).thenReturn(mock(Document.class));
		when(htmlParser.parse(any(Document.class), anyString())).thenReturn(elements);
		parser = new CourseParser(htmlLoader, htmlParser);
	}

	@Test
	public void canParseAcronym() {
		prepareTitle(A_GLO_TITLE);

		parser.addAcronym();
		Course course = parser.getCourse();

		assertEquals("GLO-2001", course.getAcronym());
	}

	@Test
	public void canParseTitle() {
		prepareTitle(A_GLO_TITLE);

		parser.addTitle();
		Course course = parser.getCourse();

		assertEquals("Systèmes d'exploitation pour ingénieurs", course.getTitle());
	}

	@Test
	public void canParseDescription() {
		prepareDescription(A_COMPLETE_DESCRIPTION);

		parser.addDescription();
		Course course = parser.getCourse();

		assertEquals("This is description.", course.getDescription());
	}

	@Test
	public void canParseCredits() {
		prepareDescription(A_COMPLETE_DESCRIPTION);

		parser.addCredits();
		Course course = parser.getCourse();

		assertEquals(3, course.getCredits());
	}

	@Test
	public void canParseCycle() {
		prepareDescription(A_COMPLETE_DESCRIPTION);

		parser.addCycle();
		Course course = parser.getCourse();

		assertEquals(Cycle.Premier, course.getCycle());
	}

	@Test
	public void canParseTimeDedicated() {
		prepareDescription(A_COMPLETE_DESCRIPTION);

		parser.addTimeDedicated();
		Course course = parser.getCourse();

		TimeDedicated timeDedicated = new TimeDedicated(3, 2, 4);
		assertEquals(timeDedicated, course.getTimeDedicated());
	}

	@Test
	public void parsesCourseIfCourseIsInDepartement() {
		prepareDescription(A_COMPLETE_DESCRIPTION);
		prepareTitle(A_GLO_TITLE);
		assertTrue(parser.courseShouldBeAddedToCourseList());
	}

	@Test
	public void doesNotParseCourseIfCourseDescriptionIsEmpty() {
		prepareDescription("");
		prepareTitle("");
		assertFalse(parser.courseShouldBeAddedToCourseList());
	}

	private void prepareDescription(String description) {
		element = element.html(description);
		when(elements.first()).thenReturn(element);
		parser.parseDescription();
	}

	private void prepareTitle(String title) {
		element = element.text(title);
		when(elements.first()).thenReturn(element);
		parser.parseTitle();
	}
}
