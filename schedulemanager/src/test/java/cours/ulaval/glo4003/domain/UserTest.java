package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class UserTest {

	private static String IDUL = "brgaa";
	private static String NAME = "Bruno Gagnon-Adam";
	private static String PASSWORD = "motdepasse";
	private static Role ROLE = Role.ROLE_Directeur;
	private static String WRONG_PASSWORD = "mauvaismotdepasse";

	private User user;

	@Before
	public void setUp() {
		user = new User(IDUL, NAME, PASSWORD, ROLE);
	}

	@Test
	public void canInstantiateAUser() {

		assertNotNull(user);
	}

	@Test
	public void canGetUsername() {

		assertEquals(IDUL, user.getIdul());
	}

	@Test
	public void canGetName() {

		assertEquals(NAME, user.getName());
	}

	@Test
	public void canValidateCredentials() {

		assertTrue(user.validateCredentials(PASSWORD));
	}

	@Test
	public void canValidateWrongCredentials() {

		assertFalse(user.validateCredentials(WRONG_PASSWORD));
	}

	@Test
	public void canAddRoleOfAUser() {
		user.addRole(Role.ROLE_Enseignant);

		assertTrue(user.getRoles().contains(Role.ROLE_Enseignant));
	}
}
