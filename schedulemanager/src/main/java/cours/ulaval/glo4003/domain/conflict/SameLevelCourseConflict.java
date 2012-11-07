package cours.ulaval.glo4003.domain.conflict;

public class SameLevelCourseConflict extends Conflict {

	// for serialzation purpose only
	public SameLevelCourseConflict() {
	}

	public SameLevelCourseConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(50);
	}

}
