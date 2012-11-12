package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameLevelCourseConflict;

public class Section {
	private String nrc;
	private String group;

	private String personInCharge;
	private List<String> teachers;
	private TeachMode teachMode;
	private TimeDedicated timeDedicated;
	private String courseAcronym;
	private List<TimeSlot> courseTimeSlots;
	private TimeSlot labTimeSlot;

	public Section() {
	}

	public Section(String nrc, String group, String personInCharge, List<String> teachers, TeachMode teachMode, TimeDedicated timeDedicated,
			String courseAcronym, List<TimeSlot> courseTimeSlots, TimeSlot labTimeSlot) {
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

	public List<TimeSlot> getCoursesAndLabTimeSlots() {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		if (courseTimeSlots != null && !courseTimeSlots.isEmpty()) {
			timeSlots.addAll(courseTimeSlots);
		}
		if (labTimeSlot != null) {
			timeSlots.add(labTimeSlot);
		}
		return timeSlots;
	}

	public List<Conflict> generateConcomittingConflicts(Section otherSection) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		for (TimeSlot sectionTimeSlots : getCoursesAndLabTimeSlots()) {
			for (TimeSlot otherSectionTimeSlots : otherSection.getCoursesAndLabTimeSlots()) {
				if (sectionTimeSlots.isOverlapping(otherSectionTimeSlots)) {
					ConcomittingCoursesConflict conflict = new ConcomittingCoursesConflict(courseAcronym, otherSection.getCourseAcronym());
					conflicts.add(conflict);
				}
			}
		}
		return conflicts;
	}

	public boolean hasTeacher(String teacher) {
		return teachers.contains(teacher);
	}

	public boolean isOverlaping(Section section) {
		for (TimeSlot timeSlot : getCoursesAndLabTimeSlots()) {
			for (TimeSlot secondTimeSlot : section.getCoursesAndLabTimeSlots()) {
				if (timeSlot.isOverlapping(secondTimeSlot) && !timeSlot.equals(secondTimeSlot)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Conflict> generateSameLevelCoursesConflicts(Section otherSection) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		for (TimeSlot sectionTimeSlots : getCoursesAndLabTimeSlots()) {
			for (TimeSlot otherSectionTimeSlots : otherSection.getCoursesAndLabTimeSlots()) {
				if (sectionTimeSlots.isOverlapping(otherSectionTimeSlots)) {
					SameLevelCourseConflict conflict = new SameLevelCourseConflict(courseAcronym, otherSection.getCourseAcronym());
					conflicts.add(conflict);
				}
			}
		}
		return conflicts;
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

	public TimeSlot getLabTimeSlot() {
		return labTimeSlot;
	}

	public void setLabTimeSlot(TimeSlot labTimeSlot) {
		this.labTimeSlot = labTimeSlot;
	}

}
