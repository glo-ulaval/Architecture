package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private static String IDUL = "brgaa";
	private static String NAME = "Bruno Gagnon-Adam";
	private static String PASSWORD = "motdepasse";
	private static Role ROLE = Role.ROLE_Directeur;
	private static String WRONG_PASSWORD = "mauvaismotdepasse";
	private static String VALID_EMAIL_ADRESS = "email@schedulemanager.com";
	private static String INVALID_EMAIL_ADRESS = "herpetyderp";
	private static Notification notification;
	private static final String A_PATH = "path";

	private User user;

	@Before
	public void setUp() {
		user = new User(IDUL, NAME, PASSWORD, ROLE);
		notification = new Notification(Notification.NEW_SCHEDULE, A_PATH);
		user.addNotification(notification);
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
	public void canVerifyInvalidEmailAdress() {
		user.setEmailAddress(INVALID_EMAIL_ADRESS);

		assertFalse(user.hasValidEmailAdress());
	}

	@Test
	public void canVerifyEmptyEmailAdress() {

		assertFalse(user.hasValidEmailAdress());
	}

	@Test
	public void canVerifyValidEmailAdress() {
		user.setEmailAddress(VALID_EMAIL_ADRESS);

		assertTrue(user.hasValidEmailAdress());
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

	@Test
	public void canAddNotificationToUser() {

		assertTrue(user.hasNotification());
	}

	@Test
	public void canRemoveNotificationToUser() {
		user.removeNotification(notification);

		assertFalse(user.getNotifications().contains(notification));
	}

	@Test
	public void cannotAddMoreThanOnceTheSameNotification() {
		int numberOfNotifications = user.getNotifications().size();
		user.addNotification(notification);

		assertEquals(numberOfNotifications, user.getNotifications().size());
	}
}
