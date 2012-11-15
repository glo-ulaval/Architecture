package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class CourseSlotModel {

	private String timeStart;
	private String timeEnd;
	private String nrc;
	private String acronym;
	private String group;
	private Boolean isLab;
	private String dayOfWeek;
	private Integer duration;
	private List<ConflictModel> conflicts = new ArrayList<ConflictModel>();

	public CourseSlotModel() {

	}

	public CourseSlotModel(Section section, TimeSlot timeSlot, Boolean isLab) {
		this.nrc = section.getNrc();
		this.group = section.getGroup();
		this.acronym = section.getCourseAcronym();
		this.timeStart = timeSlot.getStartTime().toString();
		this.timeEnd = timeSlot.getEndTime().toString();
		this.isLab = isLab;
		this.dayOfWeek = timeSlot.getDayOfWeek().toString();
		this.duration = timeSlot.getDuration();
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public void addConflict(Conflict conflict) {
		conflicts.add(new ConflictModel(conflict));
	}

	public void removeConflict(Conflict conflict) {
		conflicts.remove(conflict);
	}

	public List<ConflictModel> getConflicts() {
		return conflicts;
	}

	public void setConflicts(List<ConflictModel> conflicts) {
		this.conflicts = conflicts;
	}

}
