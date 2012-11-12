package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class AvailabilityIT {

	private static final String UN_IDUL = "enseignant";
	private static final String JSON_STRING = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,2,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";

	@Test
	public void canParseJSONToAvailabilityModel() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		assertNotNull(availabilityModel);
		assertEquals(AvailabilityLevel.Available, availabilityModel.getMonday().get(0));
		assertEquals(AvailabilityLevel.Unavailable, availabilityModel.getTuesday().get(12));
		assertEquals(AvailabilityLevel.PreferedNotAvailable, availabilityModel.getTuesday().get(0));
	}

	@Test
	public void canConvertAvailabilityModelToAvailabilities() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availability availabilities = availabilityModel.toAvailability(UN_IDUL);

		assertNotNull(availabilities);
		assertEquals(UN_IDUL, availabilities.getIdul());
		assertEquals(availabilityModel.getMonday(), availabilities.getMonday());
		assertEquals(availabilityModel.getTuesday(), availabilities.getTuesday());
		assertEquals(availabilityModel.getWednesday(), availabilities.getWednesday());
		assertEquals(availabilityModel.getThursday(), availabilities.getThursday());
		assertEquals(availabilityModel.getFriday(), availabilities.getFriday());
	}
}
