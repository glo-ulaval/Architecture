package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.TimeSlot.DayOfWeek;

public class Availability {

	private String idul;

	private List<AvailabilityLevel> monday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> tuesday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> wednesday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> thursday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> friday = new ArrayList<AvailabilityLevel>();

	public Availability() {
		super();
	}

	public List<AvailabilityLevel> getListWithDay(DayOfWeek day) {
		switch (day) {
		case FRIDAY:
			return friday;
		case MONDAY:
			return monday;
		case THURSDAY:
			return thursday;
		case TUESDAY:
			return tuesday;
		case WEDNESDAY:
			return wednesday;
		default:
			return null;
		}
	}

	public AvailabilityLevel isAvailableForTimeSlot(TimeSlot timeSlot) {
		int timeSlotBeginningIndex = timeSlot.getStartTime().getHour() - 8;
		List<AvailabilityLevel> day = getListWithDay(timeSlot.getDayOfWeek());
		for (int i = 0; i < timeSlot.getDuration(); i++) {
			if (day.get(timeSlotBeginningIndex + i) == AvailabilityLevel.Unavailable) {
				return AvailabilityLevel.Unavailable;
			} else if (day.get(timeSlotBeginningIndex + i) == AvailabilityLevel.PreferedNotAvailable) {
				return AvailabilityLevel.PreferedNotAvailable;
			}
		}
		return AvailabilityLevel.Available;
	}

	public List<TimeSlot> generatePossibleTimeSlotsForCourse(Integer timeSlotDuration) {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		possibleTimeSlots.addAll(generatePossibleTimeSlotForDay(monday, DayOfWeek.MONDAY, timeSlotDuration));
		possibleTimeSlots.addAll(generatePossibleTimeSlotForDay(tuesday, DayOfWeek.TUESDAY, timeSlotDuration));
		possibleTimeSlots.addAll(generatePossibleTimeSlotForDay(wednesday, DayOfWeek.WEDNESDAY, timeSlotDuration));
		possibleTimeSlots.addAll(generatePossibleTimeSlotForDay(thursday, DayOfWeek.THURSDAY, timeSlotDuration));
		possibleTimeSlots.addAll(generatePossibleTimeSlotForDay(friday, DayOfWeek.FRIDAY, timeSlotDuration));
		return possibleTimeSlots;
	}

	public List<TimeSlot> generatePossibleTimeSlotsForLab(Integer timeSlotDuration) {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		for (int i = 0; i <= friday.size() - timeSlotDuration; i++) {
			if (isAvailableForDuration(timeSlotDuration, friday, i)) {
				possibleTimeSlots.add(new TimeSlot(new Time(i + 8, 30), 2, DayOfWeek.FRIDAY));
			}
		}
		return possibleTimeSlots;
	}

	public String getIdul() {
		return idul;
	}

	public void setIdul(String idul) {
		this.idul = idul;
	}

	public List<AvailabilityLevel> getMonday() {
		return monday;
	}

	public void setMonday(List<AvailabilityLevel> monday) {
		this.monday = monday;
	}

	public List<AvailabilityLevel> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<AvailabilityLevel> tuesday) {
		this.tuesday = tuesday;
	}

	public List<AvailabilityLevel> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<AvailabilityLevel> wednesday) {
		this.wednesday = wednesday;
	}

	public List<AvailabilityLevel> getThursday() {
		return thursday;
	}

	public void setThursday(List<AvailabilityLevel> thursday) {
		this.thursday = thursday;
	}

	public List<AvailabilityLevel> getFriday() {
		return friday;
	}

	public void setFriday(List<AvailabilityLevel> friday) {
		this.friday = friday;
	}

	private List<TimeSlot> generatePossibleTimeSlotForDay(List<AvailabilityLevel> day, DayOfWeek dayOfWeek, int duration) {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		for (int i = 0; i <= day.size() - duration; i++) {
			if (isAvailableForDuration(duration, day, i)) {
				possibleTimeSlots.add(new TimeSlot(new Time(i + 8, 30), 3, dayOfWeek));
			}
		}
		return possibleTimeSlots;
	}

	private boolean isAvailableForDuration(int duration, List<AvailabilityLevel> day, int index) {
		boolean available = true;
		for (int i = 0; i < duration; i++) {
			if (day.get(index + i) != AvailabilityLevel.Available) {
				available = false;
			}
		}
		return available;
	}
}