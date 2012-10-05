package cours.ulaval.glo4003.model;

import java.util.Calendar;

public class TimeSlot {

	private Calendar startTime;
	private Calendar endTime;
	private Integer duration;

	public TimeSlot(Calendar startTime, Integer duration) {
		this.startTime = startTime;
		this.duration = duration;
		calculateEndTime();
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	private void calculateEndTime() {
		endTime = (Calendar) startTime.clone();
		endTime.add(Calendar.HOUR, duration);
	}
}
