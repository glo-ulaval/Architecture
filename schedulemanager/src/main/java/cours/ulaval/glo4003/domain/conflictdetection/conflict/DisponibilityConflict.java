package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class DisponibilityConflict extends ConflictWithTeacher {

	// for serialization purpose only
	public DisponibilityConflict() {
	}

	public DisponibilityConflict(String aNrc, String teacher, TimeSlot firstTimeSlot) {
		super(aNrc, "", teacher, firstTimeSlot, null);
		this.setScore(10);
	}

	@Override
	public String getDescription() {
		return "Le professeur préfère ne pas avoir de cours durant cette période.";
	}
}
