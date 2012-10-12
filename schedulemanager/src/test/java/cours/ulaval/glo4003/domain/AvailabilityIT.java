package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class AvailabilityIT {

	private static final String UN_IDUL = "UN_IDUL";
	private static final String JSON_STRING = "{\"monday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"tuesday\":[true,true,true,true,true,true,true,false,false,false,true,true,true],\"wednesday\":[true,true,true,true,true,true,true,true,true,true,false,false,false],\"thursday\":[false,false,false,false,false,true,true,true,true,true,true,true,true],\"friday\":[true,true,true,true,true,true,true,true,true,true,false,false,false]}";
	private static final int EIGHT_O_CLOCK = 8;
	private static final int THIRTY_MINUTES = 30;
	private static final int A_SEVEN_HOUR_DURATION = 7;

	private static final int EIGHTEEN_O_CLOCK = 6;
	private static final int A_THREE_HOUR_DURATION = 3;

	@Test
	public void canParseJSONToavailabilityModel()
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		assertNotNull(availabilityModel);
		assertTrue(availabilityModel.getMonday().get(0));
		assertFalse(availabilityModel.getMonday().get(8));
	}

	@Test
	public void canConvertAvailabilityModelToAvailabilities()
			throws Exception {
		Time startTime = generateStartTime(EIGHT_O_CLOCK, THIRTY_MINUTES);
		Integer duration = A_SEVEN_HOUR_DURATION;
		TimeSlot timeSlot = new TimeSlot(startTime, duration, DayOfWeek.MONDAY);
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availability availabilities = new Availability(availabilityModel, UN_IDUL);

		assertNotNull(availabilities);
		assertEquals(timeSlot.getDayOfWeek(), availabilities.getAvailabilities().get(0).getDayOfWeek());
		assertEquals(timeSlot.getStartTime().getMinute(), availabilities.getAvailabilities().get(0).getStartTime().getMinute());
		assertEquals(timeSlot.getStartTime().getHour(), availabilities.getAvailabilities().get(0).getStartTime().getHour());
		assertEquals(timeSlot.getDuration(), availabilities.getAvailabilities().get(0).getDuration());

	}

	private Time generateStartTime(int hour, int minutes) {
		Time startTime = new Time(hour, minutes);
		return startTime;
	}
}
