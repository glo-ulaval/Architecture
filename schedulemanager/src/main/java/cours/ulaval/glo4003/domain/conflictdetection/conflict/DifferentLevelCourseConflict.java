package cours.ulaval.glo4003.domain.conflictdetection.conflict;


public class DifferentLevelCourseConflict extends Conflict {

	// for serialization purpose only
	public DifferentLevelCourseConflict() {
	}

	public DifferentLevelCourseConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(30);
	}

}
