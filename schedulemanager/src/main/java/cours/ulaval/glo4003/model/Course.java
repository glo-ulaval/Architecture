package cours.ulaval.glo4003.model;

public class Course {
	
	private String acronym;
	private int number;
	private String title;
	private Integer credits;
	private String description;
	
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public String getAccronym() {
		return acronym;
	}
	public void setAccronym(String accronym) {
		this.acronym = accronym;
	}
	public int getNo() {
		return number;
	}
	public void setNo(int no) {
		this.number = no;
	}
	public String getTitle() {
		return title;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	// private cycles
	// private prealables
}
