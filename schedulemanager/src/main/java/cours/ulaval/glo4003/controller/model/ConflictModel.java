package cours.ulaval.glo4003.controller.model;

import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class ConflictModel {

	private String description;
	private String teacher;
	private String firstNrc;
	private String dayOfWeek;
	private String secondNrc;
	private String firstStartTime;
	private String firstEndTime;
	private String secondStartTime;
	private String secondEndTime;

	public ConflictModel() {

	}

	public ConflictModel(Conflict conflict) {
		this.description = conflict.getDescription();
		this.firstNrc = conflict.getFirstNrc();
		this.secondNrc = conflict.getSecondNrc();
		TimeSlot firstTimeSlot = conflict.getFirstTimeSlot();
		TimeSlot secondTimeSlot = conflict.getSecondTimeSlot();
		this.dayOfWeek = firstTimeSlot.getDayOfWeek().toString();
		this.firstStartTime = firstTimeSlot.getStartTime().toString();
		this.firstEndTime = firstTimeSlot.getEndTime().toString();
		this.teacher = conflict.getTeacher();
		if (secondTimeSlot != null) {
			this.secondStartTime = secondTimeSlot.getStartTime().toString();
			this.secondEndTime = secondTimeSlot.getEndTime().toString();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getFirstNrc() {
		return firstNrc;
	}

	public void setFirstNrc(String firstNrc) {
		this.firstNrc = firstNrc;
	}

	public String getDayOfWeek() {
		String day = "";
		if (dayOfWeek.equals("MONDAY")) {
			day = "Lundi";
		} else if (dayOfWeek.equals("TUESDAY")) {
			day = "Mardi";
		} else if (dayOfWeek.equals("WEDNESDAY")) {
			day = "Mercredi";
		} else if (dayOfWeek.equals("THURSDAY")) {
			day = "Jeudi";
		} else if (dayOfWeek.equals("FRIDAY")) {
			day = "Vendredi";
		}
		return day;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getSecondNrc() {
		return secondNrc;
	}

	public void setSecondNrc(String secondNrc) {
		this.secondNrc = secondNrc;
	}

	public String getFirstStartTime() {
		return firstStartTime;
	}

	public void setFirstStartTime(String firstStartTime) {
		this.firstStartTime = firstStartTime;
	}

	public String getFirstEndTime() {
		return firstEndTime;
	}

	public void setFirstEndTime(String firstEndTime) {
		this.firstEndTime = firstEndTime;
	}

	public String getSecondStartTime() {
		return secondStartTime;
	}

	public void setSecondStartTime(String secondStartTime) {
		this.secondStartTime = secondStartTime;
	}

	public String getSecondEndTime() {
		return secondEndTime;
	}

	public void setSecondEndTime(String secondEndTime) {
		this.secondEndTime = secondEndTime;
	}
}
