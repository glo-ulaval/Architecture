package cours.ulaval.glo4003.domain.conflictdetection.conflict;

public abstract class Conflict {
	private String firstNrc;
	private String secondNrc;
	private int score;

	// for serialization purpose only
	public Conflict() {
	}

	public Conflict(String aNrc, String anotherNrc) {
		this.firstNrc = aNrc;
		this.secondNrc = anotherNrc;
	}

	public String getFirstNrc() {
		return firstNrc;
	}

	public void setFirstNrc(String firstNrc) {
		this.firstNrc = firstNrc;
	}

	public String getSecondNrc() {
		return secondNrc;
	}

	public void setSecondNrc(String secondNrc) {
		this.secondNrc = secondNrc;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
