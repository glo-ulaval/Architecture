package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public abstract class ConflictWithTeacher extends Conflict {

	private String teacher;

	// for serialization purpose only
	public ConflictWithTeacher() {
	}

	public ConflictWithTeacher(String aNrc, String anotherNrc, String teacher, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		super(aNrc, anotherNrc, firstTimeSlot, secondTimeSlot);
		this.teacher = teacher;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}
