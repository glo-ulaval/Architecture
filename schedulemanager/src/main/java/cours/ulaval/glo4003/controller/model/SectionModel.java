package cours.ulaval.glo4003.controller.model;

import java.util.List;

import cours.ulaval.glo4003.domain.Semester;

public class SectionModel {

	private String acronym;
	private List<String> days;
	private String group;
	private Integer hoursInClass;
	private Integer hoursAtHome;
	private Integer hoursInLab;
	private String laboTimeSlotStart;
	private String laboTimeSlotEnd;
	private Integer nrc;
	private String personInCharge;
	private Semester semester;
	private List<String> teachers;
	private String teachMode;
	private List<String> timeSlotStarts;
	private List<String> timeSlotEnds;

	public SectionModel() {
		this.nrc = 00000;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getHoursInClass() {
		return hoursInClass;
	}

	public void setHoursInClass(Integer hoursInClass) {
		this.hoursInClass = hoursInClass;
	}

	public Integer getHoursAtHome() {
		return hoursAtHome;
	}

	public void setHoursAtHome(Integer hoursAtHome) {
		this.hoursAtHome = hoursAtHome;
	}

	public Integer getHoursInLab() {
		return hoursInLab;
	}

	public void setHoursInLab(Integer hoursInLab) {
		this.hoursInLab = hoursInLab;
	}

	public String getLaboTimeSlotStart() {
		return laboTimeSlotStart;
	}

	public void setLaboTimeSlotStart(String laboTimeSlotStart) {
		this.laboTimeSlotStart = laboTimeSlotStart;
	}

	public String getLaboTimeSlotEnd() {
		return laboTimeSlotEnd;
	}

	public void setLaboTimeSlotEnd(String laboTimeSlotEnd) {
		this.laboTimeSlotEnd = laboTimeSlotEnd;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public List<String> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}

	public String getTeachMode() {
		return teachMode;
	}

	public void setTeachMode(String teachMode) {
		this.teachMode = teachMode;
	}

	public List<String> getTimeSlotStarts() {
		return timeSlotStarts;
	}

	public void setTimeSlotStarts(List<String> timeSlotStarts) {
		this.timeSlotStarts = timeSlotStarts;
	}

	public List<String> getTimeSlotEnds() {
		return timeSlotEnds;
	}

	public void setTimeSlotEnds(List<String> timeSlotEnds) {
		this.timeSlotEnds = timeSlotEnds;
	}

}
