package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public class SameLevelCourseConflict extends Conflict {

	// for serialzation purpose only
	public SameLevelCourseConflict() {
	}

	public SameLevelCourseConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(50);
	}

	@Override
	public String getDescription() {
		return "Les deux cours sont du même niveau et ne peuvent être suivis en même temps.";
	}
}
