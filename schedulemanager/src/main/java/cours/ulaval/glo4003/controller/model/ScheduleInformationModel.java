package cours.ulaval.glo4003.controller.model;

import cours.ulaval.glo4003.domain.Schedule;

public class ScheduleInformationModel {

	private String year;
	private String semester;
	private String id;
	private int score;

	public ScheduleInformationModel(Schedule schedule) {
		this.year = schedule.getYear();
		this.semester = schedule.getSemester().toString();
		this.id = schedule.getId();
		this.score = schedule.getScore();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
