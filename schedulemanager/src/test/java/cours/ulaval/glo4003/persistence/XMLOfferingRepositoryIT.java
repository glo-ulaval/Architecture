package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;

public class XMLOfferingRepositoryIT extends ITTestBase {

	private static final String NEW_OFFERING_YEAR = "2009-2010";
	private static final Semester A_SEMESTER = Semester.Automne;
	XMLOfferingRepository repository;

	@Before
	public void setUp() throws Exception {
		repository = new XMLOfferingRepository();
	}

	@After
	public void tearDown() throws Exception {
		repository.clearAll();
	}

	@Test
	public void canGetYearsWithoutOfferings() throws Exception {
		List<String> years = repository.findYears();

		assertEquals(0, years.size());
	}

	@Test
	public void canGetOfferings() throws Exception {
		Offering offering = new Offering();
		offering.addCourse("GLO-2003");
		offering.setYear(NEW_OFFERING_YEAR);
		repository.store(offering);

		List<String> years = repository.findYears();
		offering = repository.find(NEW_OFFERING_YEAR);

		assertTrue(years.contains(NEW_OFFERING_YEAR));
		assertEquals(NEW_OFFERING_YEAR, offering.getYear());
		assertEquals(1, offering.getOffering().size());
		assertTrue(offering.getOffering().contains("GLO-2003"));
	}

	@Test
	public void canSaveAnOffering() throws Exception {
		Offering offering = new Offering();
		offering.addCourse("IFT-1001");
		offering.addCourse("IFT-1002");
		offering.addCourse("IFT-1003");
		offering.addCourse("IFT-1004");
		offering.addCourse("IFT-1005");
		offering.setYear(NEW_OFFERING_YEAR);

		repository.store(offering);

		XMLOfferingRepository refreshedRepository = new XMLOfferingRepository();

		List<String> storedOfferingYears = refreshedRepository.findYears();
		Offering storedOffering = refreshedRepository.find(NEW_OFFERING_YEAR);

		assertTrue(storedOfferingYears.contains(NEW_OFFERING_YEAR));
		assertTrue(storedOffering.getOffering().contains("IFT-1001"));
	}

	@Test
	public void canDeleteAnOffering() throws Exception {
		Offering offering = new Offering();
		ArrayList<String> acronyms = new ArrayList<String>();
		acronyms.add("IFT-2001");
		acronyms.add("IFT-2002");
		acronyms.add("IFT-2003");
		offering.setOffering(acronyms);
		offering.setYear("2007-2008");
		repository.store(offering);

		repository.delete("2007-2008", A_SEMESTER);

		XMLOfferingRepository refreshedRepository = new XMLOfferingRepository();
		List<String> storedOfferingYears = refreshedRepository.findYears();
		assertFalse(storedOfferingYears.contains("2007-2008"));
	}

	@AfterClass
	public static void reset() throws Exception {
		XMLOfferingRepository repo = new XMLOfferingRepository();
		repo.delete(NEW_OFFERING_YEAR, A_SEMESTER);
		repo.delete(NEW_OFFERING_YEAR);
	}
}
