package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class UnavailableTeacherConflict extends Conflict {
	public static final int SCORE = 40;

	// for serialization purpose only
	public UnavailableTeacherConflict() {
	}

	public UnavailableTeacherConflict(String aNrc, String teacher, TimeSlot firstTimeSlot) {
		super(aNrc, "", teacher, firstTimeSlot, null);
		this.setScore(SCORE);
	}

	@Override
	public String getDescription() {
		return "Le professeur n'est pas disponible durant la période du cours.";
	}
}
