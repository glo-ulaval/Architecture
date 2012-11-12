package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;

public class Availability {

	private String idul;

	private List<AvailabilityLevel> monday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> tuesday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> wednesday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> thursday = new ArrayList<AvailabilityLevel>();
	private List<AvailabilityLevel> friday = new ArrayList<AvailabilityLevel>();

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

}