package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class SameLevelCourseConflict extends Conflict {
	public static final int SCORE = 50;

	// for serialzation purpose only
	public SameLevelCourseConflict() {
	}

	public SameLevelCourseConflict(String aNrc, String anotherNrc, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, "", firstTimeSlot, secondTimeSlot);
		this.setScore(SCORE);
	}

	@Override
	public String getDescription() {
		return "Les deux cours sont du même niveau et ne peuvent être suivis en même temps.";
	}
}
