package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;

public class XMLUserRepositoryTest {

	XMLUserRepository repository;

	@Before
	public void setUp() {
		repository = new XMLUserRepository();
	}

	@Test
	public void canFindUserByIdul() {

		User director = new User("directeur", "pass", Role.Directeur);

		assertEquals(director.getIdul(), repository.findByIdul("directeur").getIdul());
		assertEquals(director.getRole(), repository.findByIdul("directeur").getRole());
	}
}
