package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class Availabilities {

	private static final int STARTING_HOUR = 8;
	private static final int THIRTY_MINUTES = 30;
	private String idul;
	private List<TimeSlot> availibilities = new ArrayList<TimeSlot>();

	public Availabilities() {
		super();
	}

	public Availabilities(AvailabilityModel availibility, String idul) {
		findTimeSlots(availibility.getMonday(), Calendar.MONDAY);
		findTimeSlots(availibility.getTuesday(), Calendar.TUESDAY);
		findTimeSlots(availibility.getWednesday(), Calendar.WEDNESDAY);
		findTimeSlots(availibility.getThursday(), Calendar.THURSDAY);
		findTimeSlots(availibility.getFriday(), Calendar.FRIDAY);
		this.idul = idul;
	}

	private void findTimeSlots(List<Boolean> listOfBoolean, int dayOfWeek) {
		int duration = 0;
		listOfBoolean.add(false);
		for (int i = 0; i < listOfBoolean.size(); i++) {

			if (listOfBoolean.get(i)) {
				duration++;
			} else if (duration != 0) {
				int start = i - duration;

				Calendar startTime = convertToTimeSlot(start, dayOfWeek);

				availibilities.add(new TimeSlot(startTime, duration));

				duration = 0;
			}
		}
	}

	private Calendar convertToTimeSlot(int start, int dayOfWeek) {
		Calendar startTime = Calendar.getInstance();

		if (start + STARTING_HOUR > 12) {
			// Reformat to 12h format
			start = (start + STARTING_HOUR) - 12;
			startTime.set(Calendar.AM_PM, Calendar.PM);
		} else {
			start = (start + STARTING_HOUR);
			startTime.set(Calendar.AM_PM, Calendar.AM);
		}

		startTime.set(Calendar.HOUR, start);
		startTime.set(Calendar.MINUTE, THIRTY_MINUTES);
		startTime.set(Calendar.DAY_OF_WEEK, dayOfWeek);

		return startTime;
	}

	public String getIdul() {
		return idul;
	}

	public void setIdul(String idul) {
		this.idul = idul;
	}

	public List<TimeSlot> getAvailibilities() {
		return availibilities;
	}

	public void setAvailibilities(List<TimeSlot> availibilities) {
		this.availibilities = availibilities;
	}

}
