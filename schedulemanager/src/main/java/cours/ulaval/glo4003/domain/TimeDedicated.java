package cours.ulaval.glo4003.domain;

public class TimeDedicated {

	private int courseHours = 0;
	private int labsHours = 0;
	private int otherHours = 0;

	public TimeDedicated() {

	}

	public TimeDedicated(int courseHours, int labsHours, int otherHours) {
		this.courseHours = courseHours;
		this.labsHours = labsHours;
		this.otherHours = otherHours;
	}

	public int getCourseHours() {
		return courseHours;
	}

	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}

	public int getLabHours() {
		return labsHours;
	}

	public void setLabHours(int labHours) {
		this.labsHours = labHours;
	}

	public int getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(int otherHours) {
		this.otherHours = otherHours;
	}

}
