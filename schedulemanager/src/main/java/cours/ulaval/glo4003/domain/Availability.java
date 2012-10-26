package cours.ulaval.glo4003.domain;

import java.util.List;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class Availability {

	private String idul;

	private List<AvailabilityLevel> monday;
	private List<AvailabilityLevel> tuesday;
	private List<AvailabilityLevel> wednesday;
	private List<AvailabilityLevel> thursday;
	private List<AvailabilityLevel> friday;

	public Availability() {
		super();
	}

	public Availability(AvailabilityModel availability, String idul) {
		monday = availability.getMonday();
		tuesday = availability.getTuesday();
		wednesday = availability.getWednesday();
		thursday = availability.getThursday();
		friday = availability.getFriday();

		this.idul = idul;
	}

	public String getIdul() {
		return idul;
	}

	public void setIdul(String idul) {
		this.idul = idul;
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

	public AvailabilityModel getModel() {

		AvailabilityModel model = new AvailabilityModel();

		model.setMonday(this.monday);
		model.setTuesday(this.tuesday);
		model.setWednesday(this.wednesday);
		model.setThursday(this.thursday);
		model.setFriday(this.friday);

		return model;
	}
}