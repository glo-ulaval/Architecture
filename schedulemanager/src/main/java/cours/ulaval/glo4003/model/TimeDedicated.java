package cours.ulaval.glo4003.model;

public class TimeDedicated {

	private int courseHours = 0;
	private int labsHours = 0;
	private int otherHours = 0;

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

	public int getOthersHours() {
		return otherHours;
	}

	public void setOtherHours(int otherHours) {
		this.otherHours = otherHours;
	}

}
