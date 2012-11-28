package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;
import cours.ulaval.glo4003.persistence.XMLCourseRepository;
import cours.ulaval.glo4003.persistence.XMLProgramSheetRepository;

public class Section {

	private ProgramSheetRepository programSheetRepository;

	private CourseRepository courseRepository;

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
		initializeRepositories();
	}

	public Section(String nrc, String group, String personInCharge, List<String> teachers, TeachMode teachMode,
			TimeDedicated timeDedicated, String courseAcronym, List<TimeSlot> courseTimeSlots, TimeSlot labTimeSlot) {
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

		initializeRepositories();
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

	public boolean areSameLevel(Section otherSection) {
		ProgramSheet iftProgramSheet = programSheetRepository.getProgramSheetIFT();
		ProgramSheet gloProgramSheet = programSheetRepository.getProgramSheetGLO();
		Boolean areSameLevel = false;
		if (gloProgramSheet.areCoursesSameLevel(courseAcronym, otherSection.getCourseAcronym())) {
			areSameLevel = true;
		}
		if (iftProgramSheet.areCoursesSameLevel(courseAcronym, otherSection.getCourseAcronym())) {
			areSameLevel = true;
		}
		return areSameLevel;
	}

	public boolean areConcomitting(Section otherSection) {
		Course course = courseRepository.findByAcronym(courseAcronym);
		Course otherCourse = courseRepository.findByAcronym(otherSection.getCourseAcronym());
		Boolean areConcomitting = false;
		if (course.isConcomitting(otherCourse)) {
			areConcomitting = true;
		}
		if (otherCourse.isConcomitting(course)) {
			areConcomitting = true;
		}
		return areConcomitting;
	}

	public boolean isSupposedToHaveLab() {
		return (timeDedicated.getLabHours() > 0);
	}

	public Section clone() {
		Section section = new Section(nrc, group, personInCharge, null, teachMode, null, courseAcronym, null, null);
		if (labTimeSlot != null) {
			section.setLabTimeSlot(labTimeSlot.clone());
		}
		if (courseTimeSlots != null) {
			List<TimeSlot> coursesTimeSlot = new ArrayList<TimeSlot>();
			for (TimeSlot timeSlot : courseTimeSlots) {
				coursesTimeSlot.add(timeSlot.clone());
			}
			section.setCourseTimeSlots(coursesTimeSlot);
		}
		if (teachers != null) {
			List<String> clonedTeachers = new ArrayList<String>();
			for (String teacher : teachers) {
				clonedTeachers.add(teacher);
			}
			section.setTeachers(clonedTeachers);
		}
		if (timeDedicated != null) {
			section.setTimeDedicated(new TimeDedicated(timeDedicated.getCourseHours(), timeDedicated.getLabHours(), timeDedicated
					.getOtherHours()));
		}
		return section;

	}

	public boolean hasTeacher(String teacher) {
		return teachers.contains(teacher);
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

	private void initializeRepositories() {
		try {
			programSheetRepository = new XMLProgramSheetRepository();
			courseRepository = new XMLCourseRepository();
		} catch (Exception e) {

		}
	}

	// WARNING - this method is for test purpose only
	public void setProgramSheetRepository(ProgramSheetRepository programSheetRepository) {
		this.programSheetRepository = programSheetRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

}
