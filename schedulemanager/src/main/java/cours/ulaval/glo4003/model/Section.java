package cours.ulaval.glo4003.model;

import java.util.List;

public class Section {
	private String nrc;
	private String group;

	private String personInCharge;
	private List<String> teachers;
	private TeachMode teachMode;
	private TimeDedicated timeDedicated;
	private String courseAcronym;
	private List<TimeSlot> courseTimeSlots;
	private List<TimeSlot> labTimeSlot;

	public Section() {
	}

	public Section(String nrc, String group, String personInCharge, List<String> teachers, TeachMode teachMode,
			TimeDedicated timeDedicated, String courseAcronym, List<TimeSlot> courseTimeSlots, List<TimeSlot> labTimeSlot) {
		super();
		this.nrc = nrc;
		this.group = group;
		this.personInCharge = personInCharge;
		this.teachers = teachers;
		this.teachMode = teachMode;
		this.timeDedicated = timeDedicated;
		this.courseAcronym = courseAcronym;
		this.courseTimeSlots = courseTimeSlots;
		this.labTimeSlot = labTimeSlot;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public List<String> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}

	public TeachMode getTeachMode() {
		return teachMode;
	}

	public void setTeachMode(TeachMode teachMode) {
		this.teachMode = teachMode;
	}

	public TimeDedicated getTimeDedicated() {
		return timeDedicated;
	}

	public void setTimeDedicated(TimeDedicated timeDedicated) {
		this.timeDedicated = timeDedicated;
	}

	public String getCourseAcronym() {
		return courseAcronym;
	}

	public void setCourseAcronym(String courseAcronym) {
		this.courseAcronym = courseAcronym;
	}

	public List<TimeSlot> getCourseTimeSlots() {
		return courseTimeSlots;
	}

	public void setCourseTimeSlots(List<TimeSlot> courseTimeSlots) {
		this.courseTimeSlots = courseTimeSlots;
	}

	public List<TimeSlot> getLabTimeSlot() {
		return labTimeSlot;
	}

	public void setLabTimeSlot(List<TimeSlot> labTimeSlot) {
		this.labTimeSlot = labTimeSlot;
	}

}
