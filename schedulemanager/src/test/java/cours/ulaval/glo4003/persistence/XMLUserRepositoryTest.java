package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.domain.Notification;
import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;

public class XMLUserRepositoryTest {

	private static final String VALID_IDUL = "abcde";

	@Mock
	private XMLSerializer<UserXMLWrapper> mockedSerializer;
	@Mock
	private User user;

	@InjectMocks
	private XMLUserRepository repository;

	private User teacher;
	private User director;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		repository = new XMLUserRepository(mockedSerializer);
		when(user.getIdul()).thenReturn(VALID_IDUL);

		createUsers();
	}

	public void createUsers() throws Exception {

		teacher = new User("enseignant", "Enseignant", "pass", Role.ROLE_Enseignant);
		repository.store(teacher);

		director = new User("directeur", "Directeur", "pass", Role.ROLE_Directeur);
		director.addRole(Role.ROLE_Enseignant);
		director.addRole(Role.ROLE_Usager);
		repository.store(director);

		User responsable = new User("responsable", "Responsable", "pass", Role.ROLE_Responsable);
		repository.store(responsable);

		User administrator = new User("administrateur", "Administrateur", "pass", Role.ROLE_Administrateur);
		repository.store(administrator);

		User utilisateur = new User("utilisateur", "Utilisateur", "pass", Role.ROLE_Usager);
		repository.store(utilisateur);
	}

	@Test
	public void canStoreAUser() throws Exception {
		repository.store(user);

		assertEquals(user, repository.findByIdul(VALID_IDUL));
	}

	@Test
	public void canFindUserByIdul() throws Exception {
		User utilisateur = new User("utilisateur", "Utilisateur", "pass", Role.ROLE_Usager);
		utilisateur.setEmailAddress("asd@email.com");
		utilisateur.addNotification(new Notification("message", "path"));

		repository.store(utilisateur);

		assertTrue(utilisateur.equals(repository.findByIdul("utilisateur")));
	}

	@Test
	public void canFindUserByRole() throws Exception {
		assertEquals(teacher.getIdul(), repository.findByRole(Role.ROLE_Enseignant).get(0).getIdul());
	}
}
