package cours.ulaval.glo4003.controller.model.utils;

import java.util.Comparator;

import cours.ulaval.glo4003.controller.model.CourseSlotModel;
import cours.ulaval.glo4003.domain.Time;

public class TimeSlotComparator implements Comparator<CourseSlotModel> {

	@Override
	public int compare(CourseSlotModel slot1, CourseSlotModel slot2) {
		Time slot1Time = new Time(Integer.valueOf(slot1.getTimeSlotStart().split(":")[0]), Integer.valueOf(slot1.getTimeSlotStart().split(":")[1]));
		Time slot2Time = new Time(Integer.valueOf(slot2.getTimeSlotStart().split(":")[0]), Integer.valueOf(slot2.getTimeSlotStart().split(":")[1]));

		Integer sort = null;

		if (getDayValue(slot1.getDayOfWeek()) > getDayValue(slot2.getDayOfWeek())) {
			sort = 1;
		} else if (getDayValue(slot1.getDayOfWeek()) < getDayValue(slot2.getDayOfWeek())) {
			sort = -1;
		} else {
			if (slot1Time.before(slot2Time)) {
				sort = -1;
			} else {
				sort = 1;
			}
		}

		return sort;
	}

	private Integer getDayValue(String day) {
		if (day.toLowerCase().equals("monday")) {
			return 1;
		}
		if (day.toLowerCase().equals("tuesday")) {
			return 2;
		}
		if (day.toLowerCase().equals("wednesday")) {
			return 3;
		}
		if (day.toLowerCase().equals("thursday")) {
			return 4;
		}
		if (day.toLowerCase().equals("friday")) {
			return 5;
		}
		return 0;
	}
}