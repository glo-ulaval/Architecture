package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public class SameTeacherConflict extends Conflict {

	// for serialization purpose only
	public SameTeacherConflict() {
	}

	public SameTeacherConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(50);
	}

	@Override
	public String getDescription() {
		return "Les deux cours ont le même professeur durant la même période.";
	}
}
