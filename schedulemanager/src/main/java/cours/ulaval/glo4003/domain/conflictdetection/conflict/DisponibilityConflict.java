package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class DisponibilityConflict extends Conflict {
	public static final int SCORE = 10;

	// for serialization purpose only
	public DisponibilityConflict() {
	}

	public DisponibilityConflict(String aNrc, String teacher, TimeSlot firstTimeSlot) {
		super(aNrc, "", teacher, firstTimeSlot, null);
		this.setScore(SCORE);
	}

	@Override
	public String getDescription() {
		return "Le professeur préfère ne pas avoir de cours durant cette période.";
	}
}
