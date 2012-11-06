package cours.ulaval.glo4003.domain.conflict;

public class ConcomittingCoursesConflict extends Conflict {

	// for serialization purpose only
	public ConcomittingCoursesConflict() {
	}

	public ConcomittingCoursesConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(40);
	}
}
