package cours.ulaval.glo4003.controller.model;

import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;

public class CourseSlotModel {
	private String timeStart;
	private String timeEnd;
	private String nrc;
	private String acronym;
	private String group;
	private Boolean isLab;
	private String dayOfWeek;

	public CourseSlotModel(Section section, TimeSlot timeSlot, Boolean isLab) {
		this.nrc = section.getNrc();
		this.group = section.getGroup();
		this.acronym = section.getCourseAcronym();
		this.timeStart = timeSlot.getStartTime().toString();
		this.timeEnd = timeSlot.getEndTime().toString();
		this.isLab = isLab;
		this.dayOfWeek = timeSlot.getDayOfWeek().toString();
	}

	public String getTimeSlotStart() {
		return timeStart;
	}

	public void setTimeSlotStart(String timeSlotStart) {
		this.timeStart = timeSlotStart;
	}

	public String getTimeSlotEnd() {
		return timeEnd;
	}

	public void setTimeSlotEnd(String timeSlotEnd) {
		this.timeEnd = timeSlotEnd;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean getIsLab() {
		return isLab;
	}

	public void setIsLab(Boolean isLab) {
		this.isLab = isLab;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
