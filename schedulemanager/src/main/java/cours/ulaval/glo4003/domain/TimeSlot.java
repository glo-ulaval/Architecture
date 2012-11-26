package cours.ulaval.glo4003.domain;

public class TimeSlot {
	public enum DayOfWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
	}

	private Time startTime;
	private Time endTime;
	private DayOfWeek dayOfWeek;
	private Integer duration;

	public TimeSlot() {
		this.startTime = new Time();
		this.endTime = new Time();
	}

	public TimeSlot(Time startTime, Time endTime, DayOfWeek dayOfWeek) {
		this.startTime = startTime;
		this.duration = endTime.getHour() - startTime.getHour();
		this.dayOfWeek = dayOfWeek;
		calculateEndTime();
	}

	public TimeSlot(Time startTime, Integer duration, DayOfWeek dayOfWeek) {
		this.startTime = startTime;
		this.duration = duration;
		this.dayOfWeek = dayOfWeek;
		calculateEndTime();
	}

	private void calculateEndTime() {
		endTime = new Time(startTime.getHour(), startTime.getMinute());
		endTime.addHours(duration);
	}

	public TimeSlot clone() {
		TimeSlot clonedTimeSlot = new TimeSlot();
		clonedTimeSlot.setDayOfWeek(dayOfWeek);
		clonedTimeSlot.setDuration(duration);
		clonedTimeSlot.setStartTime(new Time(startTime.getHour(), startTime.getMinute()));
		clonedTimeSlot.setEndTime(new Time(endTime.getHour(), endTime.getMinute()));
		return clonedTimeSlot;
	}

	public boolean isOverlapping(TimeSlot timeslot) {
		if (dayOfWeek != timeslot.dayOfWeek) {
			return false;
		} else {
			if (endTime.before(timeslot.getStartTime()) || endTime.equals(timeslot.getStartTime())) {
				return false;
			}

			if (startTime.after(timeslot.getEndTime()) || startTime.equals(timeslot.getEndTime())) {
				return false;
			}
		}

		return true;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}
