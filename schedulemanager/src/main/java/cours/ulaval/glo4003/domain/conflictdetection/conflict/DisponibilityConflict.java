package cours.ulaval.glo4003.domain.conflictdetection.conflict;


public class DisponibilityConflict extends Conflict {

	// for serialization purpose only
	public DisponibilityConflict() {
	}

	public DisponibilityConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(10);
	}
}
