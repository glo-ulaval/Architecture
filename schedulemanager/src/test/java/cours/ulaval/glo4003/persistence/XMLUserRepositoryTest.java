package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;

import cours.ulaval.glo4003.domain.*;

public class XMLUserRepositoryTest {

	private static final String VALID_IDUL = "abcde";

	@Mock
	private XMLSerializer<UserXMLWrapper> mockedSerializer;
	@Mock
	private User user;

	private XMLUserRepository repository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		repository = new XMLUserRepository();
		when(user.getIdul()).thenReturn(VALID_IDUL);
	}

	@Test
	public void canStoreAUser() throws Exception {
		repository.store(user);

		assertEquals(user, repository.findByIdul(VALID_IDUL));
	}

	@Test
	public void canFindUserByIdul() throws Exception {

		User director = new User("directeur", "Directeur", "pass", Role.ROLE_Directeur);

		repository.store(director);

		assertEquals(director.getIdul(), repository.findByIdul("directeur").getIdul());
		assertEquals(director.getRoles(), repository.findByIdul("directeur").getRoles());
		assertEquals(director.getName(), repository.findByIdul("directeur").getName());
	}
}
