package cours.ulaval.glo4003.controller.model;

import java.util.List;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.AvailabilityLevel;

public class AvailabilityModel {

	private List<AvailabilityLevel> monday;
	private List<AvailabilityLevel> tuesday;
	private List<AvailabilityLevel> wednesday;
	private List<AvailabilityLevel> thursday;
	private List<AvailabilityLevel> friday;
	
	public AvailabilityModel (Availability availability) {
		monday = availability.getMonday();
		tuesday = availability.getTuesday();
		wednesday = availability.getWednesday();
		thursday = availability.getThursday();
		friday = availability.getFriday();
	}

	public List<AvailabilityLevel> getMonday() {
		return monday;
	}

	public void setMonday(List<AvailabilityLevel> monday) {
		this.monday = monday;
	}

	public List<AvailabilityLevel> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<AvailabilityLevel> tuesday) {
		this.tuesday = tuesday;
	}

	public List<AvailabilityLevel> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<AvailabilityLevel> wednesday) {
		this.wednesday = wednesday;
	}

	public List<AvailabilityLevel> getThursday() {
		return thursday;
	}

	public void setThursday(List<AvailabilityLevel> thursday) {
		this.thursday = thursday;
	}

	public List<AvailabilityLevel> getFriday() {
		return friday;
	}

	public void setFriday(List<AvailabilityLevel> friday) {
		this.friday = friday;
	}

}
