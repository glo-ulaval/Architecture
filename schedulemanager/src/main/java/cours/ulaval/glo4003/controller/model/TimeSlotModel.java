package cours.ulaval.glo4003.controller.model;

import cours.ulaval.glo4003.domain.TimeSlot;

public class TimeSlotModel {

	private String startTime;
	private String endTime;
	private String dayOfWeek;
	private Integer duration;

	public TimeSlotModel() {

	}

	public TimeSlotModel(TimeSlot timeSlot) {
		this.startTime = timeSlot.getStartTime().toString();
		this.endTime = timeSlot.getEndTime().toString();
		this.dayOfWeek = SectionModel.inverseDaysAssociations.get(timeSlot.getDayOfWeek());
		this.duration = timeSlot.getDuration();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
