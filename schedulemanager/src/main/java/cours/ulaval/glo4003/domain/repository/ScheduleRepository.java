package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Semester;

public interface ScheduleRepository {

	public List<Schedule> findAll();

	public List<Schedule> findBy(String year);

	public Schedule findById(String id);

	public String getId(String year, Semester semester);

	public void store(Schedule schedule) throws Exception;

	public void delete(String id) throws Exception;

}
