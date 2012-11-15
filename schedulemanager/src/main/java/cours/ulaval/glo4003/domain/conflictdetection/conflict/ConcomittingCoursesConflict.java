package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class ConcomittingCoursesConflict extends Conflict {
	public static final int SCORE = 40;

	// for serialization purpose only
	public ConcomittingCoursesConflict() {
	}

	public ConcomittingCoursesConflict(String aNrc, String anotherNrc, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, "", firstTimeSlot, secondTimeSlot);
		this.setScore(SCORE);
	}

	@Override
	public String getDescription() {
		return "Les deux cours sont concomitants et ne peuvent être suivis en même temps.";
	}
}
