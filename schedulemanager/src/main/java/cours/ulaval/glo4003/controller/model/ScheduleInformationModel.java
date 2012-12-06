package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.ScheduleStatus;

public class ScheduleInformationModel {

	private String year;
	private String semester;
	private String id;
	private int score;
	private List<String> approvedUsers = new ArrayList<String>();
	private List<String> refusedUsers = new ArrayList<String>();

	public ScheduleInformationModel(Schedule schedule) {
		this.year = schedule.getYear();
		this.semester = schedule.getSemester().toString();
		this.id = schedule.getId();
		this.score = schedule.getScore();
		Map<String, ScheduleStatus> statuses = schedule.getStatuses();
		for (String idul : schedule.getStatuses().keySet()) {
			if (statuses.get(idul) == ScheduleStatus.Accepted) {
				approvedUsers.add(idul);
			} else if (statuses.get(idul) == ScheduleStatus.Refused) {
				refusedUsers.add(idul);
			}
		}
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

	public List<String> getApprovedUsers() {
		return approvedUsers;
	}

	public void setApprovedUsers(List<String> approvedUsers) {
		this.approvedUsers = approvedUsers;
	}

	public List<String> getRefusedUsers() {
		return refusedUsers;
	}

	public void setRefusedUsers(List<String> refusedUsers) {
		this.refusedUsers = refusedUsers;
	}
}
