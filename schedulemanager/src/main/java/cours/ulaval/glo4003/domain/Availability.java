package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class Availability {

	private static final int STARTING_HOUR = 8;
	private static final int THIRTY_MINUTES = 30;
	private String idul;
	private List<TimeSlot> availabilities = new ArrayList<TimeSlot>();
	private AvailabilityModel model;

	public Availability() {
		super();
	}

	public Availability(AvailabilityModel availability, String idul) {
		findTimeSlots(availability.getMonday(), DayOfWeek.MONDAY);
		findTimeSlots(availability.getTuesday(), DayOfWeek.TUESDAY);
		findTimeSlots(availability.getWednesday(), DayOfWeek.WEDNESDAY);
		findTimeSlots(availability.getThursday(), DayOfWeek.THURSDAY);
		findTimeSlots(availability.getFriday(), DayOfWeek.FRIDAY);
		this.idul = idul;
		this.model = availability;
	}

	private void findTimeSlots(List<Boolean> listOfBoolean, DayOfWeek dayOfWeek) {
		int duration = 0;
		listOfBoolean.add(false);
		for (int i = 0; i < listOfBoolean.size(); i++) {

			if (listOfBoolean.get(i)) {
				duration++;
			} else if (duration != 0) {
				int start = i - duration;

				TimeSlot startTime = convertToTimeSlot(start, dayOfWeek, duration);

				availabilities.add(startTime);

				duration = 0;
			}
		}
	}

	private TimeSlot convertToTimeSlot(int start, DayOfWeek dayOfWeek, int duration) {
		Time startTime = new Time(start + STARTING_HOUR, THIRTY_MINUTES);
		TimeSlot timeSlot = new TimeSlot(startTime, duration, dayOfWeek);
		return timeSlot;
	}

	public String getIdul() {
		return idul;
	}

	public void setIdul(String idul) {
		this.idul = idul;
	}

	public List<TimeSlot> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<TimeSlot> availabilities) {
		this.availabilities = availabilities;
	}

	public AvailabilityModel getModel() {
		return model;
	}

	public void setModel(AvailabilityModel model) {
		this.model = model;
	}
}
