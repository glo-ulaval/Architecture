package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cours.ulaval.glo4003.controller.model.utils.TimeSlotComparator;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;

public class CalendarModel {

	private String year;
	private String semester;
	private String id;

	private List<CourseSlotModel> courseSlots = new ArrayList<CourseSlotModel>();

	public CalendarModel(List<Section> sections, String year, String semester, String id) {
		for (Section section : sections) {
			for (TimeSlot timeSlot : section.getCourseTimeSlots()) {
				courseSlots.add(new CourseSlotModel(section, timeSlot, false));
			}
		}
		for (Section section : sections) {
			TimeSlot timeSlot = section.getLabTimeSlot();
			if (timeSlot != null) {
				courseSlots.add(new CourseSlotModel(section, timeSlot, true));
			}
		}
		Collections.sort(courseSlots, new TimeSlotComparator());

		this.year = year;
		this.semester = semester;
		this.id = id;
	}

	public List<CourseSlotModel> getCourseSlots() {
		return courseSlots;
	}

	public void setCourseSlots(List<CourseSlotModel> courseSlots) {
		this.courseSlots = courseSlots;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
