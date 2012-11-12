package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public class UnavailableTeacherConflict extends Conflict {

	// for serialization purpose only
	public UnavailableTeacherConflict() {
	}

	public UnavailableTeacherConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(40);
	}

	@Override
	public String getDescription() {
		return "Le professeur n'est pas disponible durant la période du cours.";
	}
}
