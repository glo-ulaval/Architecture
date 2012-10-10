package cours.ulaval.glo4003.controller.model;

import java.util.List;

public class AvailabilityModel {

	private List<Boolean> monday;
	private List<Boolean> tuesday;
	private List<Boolean> wednesday;
	private List<Boolean> thursday;
	private List<Boolean> friday;

	public List<Boolean> getMonday() {
		return monday;
	}

	public void setMonday(List<Boolean> monday) {
		this.monday = monday;
	}

	public List<Boolean> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<Boolean> tuesday) {
		this.tuesday = tuesday;
	}

	public List<Boolean> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<Boolean> wednesday) {
		this.wednesday = wednesday;
	}

	public List<Boolean> getThursday() {
		return thursday;
	}

	public void setThursday(List<Boolean> thursday) {
		this.thursday = thursday;
	}

	public List<Boolean> getFriday() {
		return friday;
	}

	public void setFriday(List<Boolean> friday) {
		this.friday = friday;
	}

}
