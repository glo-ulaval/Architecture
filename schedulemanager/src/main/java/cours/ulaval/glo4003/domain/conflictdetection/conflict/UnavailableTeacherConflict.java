package cours.ulaval.glo4003.domain.conflictdetection.conflict;


public class UnavailableTeacherConflict extends Conflict {

	// for serialization purpose only
	public UnavailableTeacherConflict() {
	}

	public UnavailableTeacherConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(40);
	}

}
