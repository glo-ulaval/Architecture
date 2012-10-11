package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class AvailabilitiesIT {

	private static final String UN_IDUL = "UN_IDUL";
	private static final String JSON_STRING = "{\"monday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"tuesday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"wednesday\":[true,true,true,true,true,true,true,true,true,true,false,false,false],\"thursday\":[false,false,false,false,false,true,true,true,true,true,true,true,true],\"friday\":[true,true,true,true,true,true,true,true,true,true,false,false,false]}";
	private static final int EIGHT_O_CLOCK = 8;
	private static final int THIRTY_MINUTES = 30;
	private static final int A_SEVEN_HOUR_DURATION = 7;

	private static final int EIGHTEEN_O_CLOCK = 6;
	private static final int A_THREE_HOUR_DURATION = 3;

	@Test
	public void canParseJSONToAvailibilityModel()
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availibilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		assertNotNull(availibilityModel);
		assertTrue(availibilityModel.getMonday().get(0));
		assertFalse(availibilityModel.getMonday().get(8));
	}

	@Test
	public void canConvertAvailabilityModelToAvailabilitiesWithPMValues()
			throws Exception {
		Calendar startTime = generateStartTime(EIGHTEEN_O_CLOCK, THIRTY_MINUTES, Calendar.PM);
		Integer duration = A_THREE_HOUR_DURATION;
		TimeSlot timeSlot = new TimeSlot(startTime, duration);
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availibilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availabilities availabilities = new Availabilities(availibilityModel, UN_IDUL);

		assertNotNull(availabilities);
		assertEquals(timeSlot.getStartTime().get(Calendar.DAY_OF_WEEK), availabilities.getAvailibilities().get(1).getStartTime()
				.get(Calendar.DAY_OF_WEEK));
		assertEquals(timeSlot.getStartTime().get(Calendar.MINUTE),
				availabilities.getAvailibilities().get(1).getStartTime().get(Calendar.MINUTE));
		assertEquals(timeSlot.getDuration(), availabilities.getAvailibilities().get(1).getDuration());
		assertEquals(timeSlot.getStartTime().get(Calendar.AM_PM),
				availabilities.getAvailibilities().get(1).getStartTime().get(Calendar.AM_PM));

	}

	@Test
	public void canConvertAvailabilityModelToAvailabilitiesWithAMValues()
			throws Exception {
		Calendar startTime = generateStartTime(EIGHT_O_CLOCK, THIRTY_MINUTES, Calendar.AM);
		Integer duration = A_SEVEN_HOUR_DURATION;
		TimeSlot timeSlot = new TimeSlot(startTime, duration);
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availibilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availabilities availabilities = new Availabilities(availibilityModel, UN_IDUL);

		assertNotNull(availabilities);
		assertEquals(timeSlot.getStartTime().get(Calendar.DAY_OF_WEEK), availabilities.getAvailibilities().get(0).getStartTime()
				.get(Calendar.DAY_OF_WEEK));
		assertEquals(timeSlot.getStartTime().get(Calendar.MINUTE),
				availabilities.getAvailibilities().get(0).getStartTime().get(Calendar.MINUTE));
		assertEquals(timeSlot.getDuration(), availabilities.getAvailibilities().get(0).getDuration());
		assertEquals(timeSlot.getStartTime().get(Calendar.AM_PM),
				availabilities.getAvailibilities().get(0).getStartTime().get(Calendar.AM_PM));

	}

	private Calendar generateStartTime(int hour, int minutes, int am_pm) {
		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		startTime.set(Calendar.HOUR, hour);
		startTime.set(Calendar.MINUTE, minutes);
		startTime.set(Calendar.AM_PM, am_pm);
		return startTime;
	}
}
