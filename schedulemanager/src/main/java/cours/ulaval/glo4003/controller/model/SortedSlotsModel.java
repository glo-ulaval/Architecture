package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cours.ulaval.glo4003.controller.model.utils.TimeSlotComparator;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

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
					monday.add(new CourseSlotModel(section, timeSlot, false));
					break;
				case TUESDAY:
					tuesday.add(new CourseSlotModel(section, timeSlot, false));
					break;
				case WEDNESDAY:
					wednesday.add(new CourseSlotModel(section, timeSlot, false));
					break;
				case THURSDAY:
					thursday.add(new CourseSlotModel(section, timeSlot, false));
					break;
				case FRIDAY:
					friday.add(new CourseSlotModel(section, timeSlot, false));
					break;
				default:
					break;
				}
			}
		}
		for (Section section : sections) {
			TimeSlot timeSlot = section.getLabTimeSlot();
			if (timeSlot != null) {
				switch (timeSlot.getDayOfWeek()) {
				case MONDAY:
					monday.add(new CourseSlotModel(section, timeSlot, true));
					break;
				case TUESDAY:
					tuesday.add(new CourseSlotModel(section, timeSlot, true));
					break;
				case WEDNESDAY:
					wednesday.add(new CourseSlotModel(section, timeSlot, true));
					break;
				case THURSDAY:
					thursday.add(new CourseSlotModel(section, timeSlot, true));
					break;
				case FRIDAY:
					friday.add(new CourseSlotModel(section, timeSlot, true));
					break;
				default:
					break;
				}
			}
		}
		sort();
	}

	public void addConflicts(List<Conflict> conflicts) {
		for (Conflict conflict : conflicts) {
			findCourseSlotAccordingToDay(conflict.getFirstTimeSlot(), conflict.getFirstNrc()).addConflict(conflict);
			TimeSlot secondTimeSlot = conflict.getSecondTimeSlot();

			if (secondTimeSlot != null) {
				findCourseSlotAccordingToDay(secondTimeSlot, conflict.getSecondNrc()).addConflict(conflict);
			}
		}
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

	private CourseSlotModel findCourseSlotAccordingToDay(TimeSlot slot, String nrc) {
		CourseSlotModel model = null;
		switch (slot.getDayOfWeek()) {
		case MONDAY:
			model = findCourseSlotAccordingToHours(slot, nrc, monday);
			break;
		case TUESDAY:
			model = findCourseSlotAccordingToHours(slot, nrc, tuesday);
			break;
		case WEDNESDAY:
			model = findCourseSlotAccordingToHours(slot, nrc, wednesday);
			break;
		case THURSDAY:
			model = findCourseSlotAccordingToHours(slot, nrc, thursday);
			break;
		case FRIDAY:
			model = findCourseSlotAccordingToHours(slot, nrc, friday);
			break;
		default:
			break;
		}

		return model;
	}

	private CourseSlotModel findCourseSlotAccordingToHours(TimeSlot slot, String nrc, List<CourseSlotModel> models) {
		CourseSlotModel modelToReturn = null;
		for (CourseSlotModel model : models) {
			if (model.getNrc().equals(nrc) && model.getTimeSlotStart().equals(slot.getStartTime().toString())
					&& model.getTimeSlotEnd().equals(slot.getEndTime().toString())) {
				modelToReturn = model;
				break;
			}
		}

		return modelToReturn;
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

}