package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class AvailabilityTest {

	private static final String JSON_STRING = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,2,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;
	private static final String A_IDUL = "DERP";

	private ObjectMapper mapper = new ObjectMapper();
	private AvailabilityModel availabilityModel;
	private Availability availabilities;

	@Before
	public void setUp()
			throws Exception {
		availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);
		availabilities = new Availability(availabilityModel, A_IDUL);
	}

	@Test
	public void canInstantiateAnAvailability() {
		assertNotNull(availabilities);
	}

	@Test
	public void canGetAvailabiltyListWithDay() {
		List<AvailabilityLevel> correctMonday = availabilities.getMonday();

		List<AvailabilityLevel> monday = availabilities.getListWithDay(DayOfWeek.MONDAY);

		assertEquals(monday, correctMonday);
	}

	@Test
	public void cannotObtainListWithSaturdayOrSunday() {
		List<AvailabilityLevel> sunday = availabilities.getListWithDay(DayOfWeek.SUNDAY);

		assertNull(sunday);
	}

	@Test
	public void canTellIfAvailableWithGivenTimeSlot() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		TimeSlot timeSlot = new TimeSlot(startTime, 3, DayOfWeek.MONDAY);

		AvailabilityLevel level = availabilities.isAvailableForTimeSlot(timeSlot);

		assertEquals(AvailabilityLevel.Available, level);
	}

	@Test
	public void canTellIfPreferedNotAvailableWithGivenTimeSlot() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		TimeSlot timeSlot = new TimeSlot(startTime, 3, DayOfWeek.TUESDAY);

		AvailabilityLevel level = availabilities.isAvailableForTimeSlot(timeSlot);

		assertEquals(AvailabilityLevel.PreferedNotAvailable, level);
	}

	@Test
	public void canTellIfUnavailableWithGivenTimeSlot() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		TimeSlot timeSlot = new TimeSlot(startTime, 3, DayOfWeek.TUESDAY);

		AvailabilityLevel level = availabilities.isAvailableForTimeSlot(timeSlot);

		assertEquals(AvailabilityLevel.PreferedNotAvailable, level);
	}

}
