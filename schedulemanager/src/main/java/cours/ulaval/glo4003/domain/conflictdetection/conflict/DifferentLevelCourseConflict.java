package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class DifferentLevelCourseConflict extends Conflict {

	// for serialization purpose only
	public DifferentLevelCourseConflict() {
	}

	public DifferentLevelCourseConflict(String aNrc, String anotherNrc, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, firstTimeSlot, secondTimeSlot);
		this.setScore(30);
	}

	@Override
	public String getDescription() {
		return "Les deux cours ne sont pas du même niveau et ne peuvent être suivis en même temps.";
	}
}
