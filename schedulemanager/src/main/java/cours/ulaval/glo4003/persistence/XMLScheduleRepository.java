package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.model.Schedule;
import cours.ulaval.glo4003.model.ScheduleRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLScheduleRepository implements ScheduleRepository {

	private XMLSerializer<ScheduleDTO> serializer;
	private Map<String, Schedule> schedules = new HashMap<String, Schedule>();

	public XMLScheduleRepository() throws Exception {
		serializer = new XMLSerializer<ScheduleDTO>(ScheduleDTO.class);
		parseXML();
	}

	@Override
	public List<Schedule> findAll() {
		return new ArrayList<Schedule>(schedules.values());
	}

	@Override
	public List<Schedule> findBy(String year) {
		List<Schedule> schedulesbyYear = new ArrayList<Schedule>();

		for (Schedule schedule : schedules.values()) {
			if (schedule.getYear().equals(year)) {
				schedulesbyYear.add(schedule);
			}
		}

		return schedulesbyYear;
	}

	@Override
	public void store(Schedule schedule) throws Exception {
		if (schedules.containsKey(schedule.getId())) {
			schedules.put(schedule.getId(), schedule);
		}
	}

	@Override
	public void delete(String id) throws Exception {
		if (schedules.containsKey(id)) {
			schedules.remove(id);
		}
	}

	private void parseXML() throws Exception {
		String path = ConfigManager.getConfigManager().getSchedulesFilepath();
		// List<Schedule> deserializedSchedules = serializer.deserialize());
		/*
		 * for (Offering offering : deserializedOfferings) {
		 * offerings.put(offering.getYear(), offering); }
		 */
	}

	private void saveXML() throws Exception {
		/*
		 * OfferingDTO offeringDTO = new OfferingDTO();
		 * offeringDTO.setOfferings(new
		 * ArrayList<Offering>(offerings.values()));
		 * serializer.serialize(offeringDTO,
		 * ConfigManager.getConfigManager().getOfferingsFilePath());
		 */
	}

}
