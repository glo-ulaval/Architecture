package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Prerequisite;

public class PrerequisiteTest {

	private Prerequisite prerequisite;
	private List<String> acronyms = Arrays.asList("anAcronym");

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
}
