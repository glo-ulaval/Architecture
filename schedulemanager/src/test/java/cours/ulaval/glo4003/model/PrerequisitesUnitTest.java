package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class PrerequisitesUnitTest {

	@Test
	public void canGetPrerequisitesWithNoPrerequisite() {
		Prerequisites prerequisites = new Prerequisites();

		assertEquals(0, prerequisites.getPrerequisites().size());
	}

	@Test(expected = NoSuchElementException.class)
	public void canIterateOnPrerequisitesWhenNoPrerequisite() {
		Prerequisites prerequisites = new Prerequisites();

		prerequisites.iterator().next();
	}

	@Test
	public void canIterateOnPrerequisitesWithPrerequisites() {
		Prerequisites prerequisites = new Prerequisites();
		prerequisites.setPrerequisites(getPrerequisites());

		assertNotNull(prerequisites.iterator().next());
	}

	@Test
	public void canGetPrerequisitesWithPrerequisites() {
		Prerequisites prerequisites = new Prerequisites();
		List<Prerequisite> prerequisiteList = getPrerequisites();
		prerequisites.setPrerequisites(prerequisiteList);

		assertEquals(prerequisiteList, prerequisites.getPrerequisites());
	}

	private List<Prerequisite> getPrerequisites() {
		ArrayList<Prerequisite> prerequisites = new ArrayList<Prerequisite>();
		Prerequisite prerequisite = mock(Prerequisite.class);
		prerequisites.add(prerequisite);

		return prerequisites;
	}
}
