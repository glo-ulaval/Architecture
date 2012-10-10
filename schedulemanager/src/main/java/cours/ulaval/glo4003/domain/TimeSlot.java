package cours.ulaval.glo4003.domain;

import java.util.Calendar;

public class TimeSlot {
	private Calendar startTime;
	private Calendar endTime;
	private Integer duration;

	public TimeSlot() {
	}

	public TimeSlot(Calendar startTime, Integer duration) {
		this.startTime = startTime;
		this.duration = duration;
		calculateEndTime();
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	private void calculateEndTime() {
		endTime = (Calendar) startTime.clone();
		endTime.add(Calendar.HOUR, duration);
	}
}
