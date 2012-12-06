package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cours.ulaval.glo4003.controller.NotificationController;
import cours.ulaval.glo4003.domain.repository.UserRepository;
import cours.ulaval.glo4003.persistence.ITTestBase;

public class NotificationIT extends ITTestBase {

	@Mock
	private UserRepository mockedUserRepository;

	@Mock
	private User mockedUser;

	private NotificationController notificationController;

	private static final String VALID_PATH = "/schedule/editsection/2011-2012-Automne-1/2011-2012/Automne/90111/calendar";
	private static final String VALID_MESSAGE = Notification.SECTION_MODIFIED;
	private static final Integer VALID_ID = 374543161;
	private static final String EXPECTED_JSON_WITH_ONE_NOTIFICATION = "[{\"id\":" + VALID_ID.toString() + ",\"message\":\"" + VALID_MESSAGE
			+ "\",\"path\":\"" + VALID_PATH + "\"}]";
	private static final String EXPECTED_JSON_WITH_NO_NOTIFICATION = "[]";

	private static final String VALID_IDUL = "THEUD";
	private Notification notification;
	private List<Notification> notifications;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		notificationController = new NotificationController();
		notificationController.setUserRepository(mockedUserRepository);
		notificationController.setObjectMapper(new ObjectMapper());

		notification = new Notification(VALID_MESSAGE, VALID_PATH);
		notification.setId(VALID_ID);

		notifications = new ArrayList<Notification>();

		when(mockedUserRepository.findByIdul(anyString())).thenReturn(mockedUser);
		when(mockedUser.getNotifications()).thenReturn(notifications);

	}

	@Test
	public void canAddASectionModifiedNotificationToAUser() {
		notifications.add(notification);

		assertEquals(EXPECTED_JSON_WITH_ONE_NOTIFICATION, notificationController.notification(VALID_IDUL));
	}

	@Test
	public void returnsEmptyJSONWhenNoNotifications() {

		assertEquals(EXPECTED_JSON_WITH_NO_NOTIFICATION, notificationController.notification(VALID_IDUL));
	}
}
