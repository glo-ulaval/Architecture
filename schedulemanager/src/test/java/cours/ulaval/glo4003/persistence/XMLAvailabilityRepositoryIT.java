package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.Availability;

public class XMLAvailabilityRepositoryIT extends ITTestBase {

	private static final String JSON_STRING = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,2,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";

	private static final String UN_IDUL = "enseignant";
	private XMLAvailabilityRepository repository;

	@Before
	public void setUp()
			throws Exception {
		repository = new XMLAvailabilityRepository();
	}

	@Test
	public void canSaveAvailabilities()
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availability availabilities = availabilityModel.toAvailability(UN_IDUL);

		repository.store(availabilities);

	}

	@Test
	public void canLoadAnAvailability()
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availability availabilities = availabilityModel.toAvailability(UN_IDUL);
		repository.store(availabilities);

		assertEquals(availabilities.getMonday(), repository.findByIdul(UN_IDUL).getMonday());
		assertEquals(availabilities.getTuesday(), repository.findByIdul(UN_IDUL).getTuesday());
		assertEquals(availabilities.getWednesday(), repository.findByIdul(UN_IDUL).getWednesday());
		assertEquals(availabilities.getThursday(), repository.findByIdul(UN_IDUL).getThursday());
		assertEquals(availabilities.getFriday(), repository.findByIdul(UN_IDUL).getFriday());
	}

	@Test
	public void canClearAvailabilities()
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AvailabilityModel availabilityModel = mapper.readValue(JSON_STRING, AvailabilityModel.class);

		Availability availabilities = availabilityModel.toAvailability(UN_IDUL);
		repository.store(availabilities);

		repository.clear();

		assertNull(repository.findByIdul(UN_IDUL));

	}
}
