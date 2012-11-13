package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class SameTeacherConflict extends ConflictWithTeacher {

	// for serialization purpose only
	public SameTeacherConflict() {
	}

	public SameTeacherConflict(String aNrc, String anotherNrc, String teacher, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, teacher, firstTimeSlot, secondTimeSlot);
		this.setScore(50);
	}

	@Override
	public String getDescription() {
		return "Les deux cours ont le même professeur durant la même période.";
	}
}
