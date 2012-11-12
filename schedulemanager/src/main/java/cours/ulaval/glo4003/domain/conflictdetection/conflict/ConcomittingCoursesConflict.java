package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public class ConcomittingCoursesConflict extends Conflict {

	// for serialization purpose only
	public ConcomittingCoursesConflict() {
	}

	public ConcomittingCoursesConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(40);
	}

	@Override
	public String getDescription() {
		return "Les deux cours sont concomitants et ne peuvent être suivis en même temps.";
	}
}
