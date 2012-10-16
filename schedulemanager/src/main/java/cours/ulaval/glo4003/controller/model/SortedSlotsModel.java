package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.utils.TimeSlotComparator;

public class SortedSlotsModel {

	private List<CourseSlotModel> monday;
	private List<CourseSlotModel> tuesday;
	private List<CourseSlotModel> wednesday;
	private List<CourseSlotModel> thursday;
	private List<CourseSlotModel> friday;

	public SortedSlotsModel(List<Section> sections) {
		initialize();
		for (Section section : sections) {
			for (TimeSlot timeSlot : section.getCourseTimeSlots()) {
				switch (timeSlot.getDayOfWeek()) {
				case MONDAY:
					monday.add(new CourseSlotModel(section, timeSlot));
					break;
				case TUESDAY:
					tuesday.add(new CourseSlotModel(section, timeSlot));
					break;
				case WEDNESDAY:
					wednesday.add(new CourseSlotModel(section, timeSlot));
					break;
				case THURSDAY:
					thursday.add(new CourseSlotModel(section, timeSlot));
					break;
				case FRIDAY:
					friday.add(new CourseSlotModel(section, timeSlot));
					break;
				default:
					break;
				}
			}
		}
		sort();
	}

	private void initialize() {
		monday = new ArrayList<CourseSlotModel>();
		tuesday = new ArrayList<CourseSlotModel>();
		wednesday = new ArrayList<CourseSlotModel>();
		thursday = new ArrayList<CourseSlotModel>();
		friday = new ArrayList<CourseSlotModel>();
	}

	private void sort() {
		Collections.sort(monday, new TimeSlotComparator());
		Collections.sort(tuesday, new TimeSlotComparator());
		Collections.sort(wednesday, new TimeSlotComparator());
		Collections.sort(thursday, new TimeSlotComparator());
		Collections.sort(friday, new TimeSlotComparator());
	}

	public List<CourseSlotModel> getMonday() {
		return monday;
	}

	public void setMonday(List<CourseSlotModel> monday) {
		this.monday = monday;
	}

	public List<CourseSlotModel> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<CourseSlotModel> tuesday) {
		this.tuesday = tuesday;
	}

	public List<CourseSlotModel> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<CourseSlotModel> wednesday) {
		this.wednesday = wednesday;
	}

	public List<CourseSlotModel> getThursday() {
		return thursday;
	}

	public void setThursday(List<CourseSlotModel> thursday) {
		this.thursday = thursday;
	}

	public List<CourseSlotModel> getFriday() {
		return friday;
	}

	public void setFriday(List<CourseSlotModel> friday) {
		this.friday = friday;
	}
}