package cours.ulaval.glo4003.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Time {

	private Integer hour;
	private Integer minute;

	public Time() {
	}

	public Time(Integer hour, Integer minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public void addHours(Integer hoursToAdd) {
		hour += hoursToAdd;
	}

	private String adjustTime(String time) {
		String modifiedTime = time;

		if (time.matches("\\d+:0$")) {
			modifiedTime += "0";
		}

		return modifiedTime;
	}

	public boolean after(Time time) {
		if (hour > time.getHour()) {
			return true;
		} else if (hour == time.getHour() && minute > time.getMinute()) {
			return true;
		}

		return false;
	}

	public boolean before(Time time) {
		if (hour < time.getHour()) {
			return true;
		} else if (hour == time.getHour() && minute < time.getMinute()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public Integer getHour() {
		return hour;
	}

	public Integer getMinute() {
		return minute;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	@Override
	public String toString() {
		return adjustTime(this.hour + ":" + this.minute);
	}

}
