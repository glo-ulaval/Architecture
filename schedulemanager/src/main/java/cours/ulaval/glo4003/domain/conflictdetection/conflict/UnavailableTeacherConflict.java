package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public class UnavailableTeacherConflict extends Conflict {

	private String teacher;

	// for serialization purpose only
	public UnavailableTeacherConflict() {
	}

	public UnavailableTeacherConflict(String aNrc, String teacher, TimeSlot firstTimeSlot) {
		super(aNrc, "", firstTimeSlot, null);
		this.teacher = teacher;
		this.setScore(40);
	}

	@Override
	public String getDescription() {
		return "Le professeur n'est pas disponible durant la période du cours.";
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}
