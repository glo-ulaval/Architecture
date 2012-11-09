package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PrerequisiteTest {

	private static final String AN_ACRONYM = "anAcronym";
	private Prerequisite prerequisite;
	private List<String> acronyms = Arrays.asList(AN_ACRONYM);

	@Before
	public void setUp() {
		prerequisite = new Prerequisite();
	}

	@Test
	public void canGetAcronymsWithNoAcronyms() {
		assertEquals(0, prerequisite.getAcronyms().size());
	}

	@Test
	public void canGetAcronymsWithMultipleAcronyms() {
		prerequisite.setAcronyms(acronyms);

		assertEquals(acronyms, prerequisite.getAcronyms());
	}

	@Test
	public void canSetAcronyms() {
		prerequisite.setAcronyms(acronyms);

		assertEquals(1, prerequisite.getAcronyms().size());
	}

	@Test
	public void canGetIfPrerequisiteIsConcomitant() {
		prerequisite.setIsConcomitant(true);

		assertTrue(prerequisite.getIsConcomitant());
	}

	@Test
	public void containsAcronymShouldReturnTrueIfAcronymIsAPrerequisite() {
		prerequisite.setAcronyms(acronyms);

		assertTrue(prerequisite.containsAcronym(AN_ACRONYM));
	}

	@Test
	public void containsAcronymShouldReturnFalseIfAcronymIsAPrerequisite() {
		prerequisite.setAcronyms(acronyms);

		assertFalse(prerequisite.containsAcronym("not_a_prerequisite"));
	}
}
