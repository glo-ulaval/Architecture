package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class SameTeacherConflict extends Conflict {
	public static final int SCORE = 50;

	// for serialization purpose only
	public SameTeacherConflict() {
	}

	public SameTeacherConflict(String aNrc, String anotherNrc, String teacher, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, teacher, firstTimeSlot, secondTimeSlot);
		this.setScore(SCORE);
	}

	@Override
	public String getDescription() {
		return "Les deux cours ont le même professeur durant la même période.";
	}
}
