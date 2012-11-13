package cours.ulaval.glo4003.domain.conflictdetection.conflict;

import cours.ulaval.glo4003.domain.TimeSlot;

public abstract class Conflict {
	private String firstNrc;
	private String secondNrc;
	private TimeSlot firstTimeSlot;
	private TimeSlot secondTimeSlot;
	private int score;

	// for serialization purpose only
	public Conflict() {
	}

	public Conflict(String aNrc, String anotherNrc, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
		this.firstNrc = aNrc;
		this.secondNrc = anotherNrc;
		this.firstTimeSlot = firstTimeSlot;
		this.secondTimeSlot = secondTimeSlot;
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

	public TimeSlot getFirstTimeSlot() {
		return firstTimeSlot;
	}

	public void setFirstTimeSlot(TimeSlot firstTimeSlot) {
		this.firstTimeSlot = firstTimeSlot;
	}

	public TimeSlot getSecondTimeSlot() {
		return secondTimeSlot;
	}

	public void setSecondTimeSlot(TimeSlot secondTimeSlot) {
		this.secondTimeSlot = secondTimeSlot;
	}

	public abstract String getDescription();
}
