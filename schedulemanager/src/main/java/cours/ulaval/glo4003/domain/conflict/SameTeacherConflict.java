package cours.ulaval.glo4003.domain.conflict;

public class SameTeacherConflict extends Conflict {

	// for serialization purpose only
	public SameTeacherConflict() {
	}

	public SameTeacherConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(50);
	}

}
