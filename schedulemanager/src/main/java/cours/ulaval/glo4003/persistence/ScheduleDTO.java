package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.model.Schedule;

@XmlRootElement(name = "schedules")
public class ScheduleDTO {
	private List<Schedule> schedules = new ArrayList<Schedule>();

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@XmlElement(name = "schedule")
	public List<Schedule> getSchedules() {
		return this.schedules;
	}
}
