package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public class DisponibilityConflict extends Conflict {

	// for serialization purpose only
	public DisponibilityConflict() {
	}

	public DisponibilityConflict(String aNrc, String anotherNrc) {
		super(aNrc, anotherNrc);
		this.setScore(10);
	}

	@Override
	public String getDescription() {
		return "Le professeur préfère ne pas avoir de cours durant cette période.";
	}
}
