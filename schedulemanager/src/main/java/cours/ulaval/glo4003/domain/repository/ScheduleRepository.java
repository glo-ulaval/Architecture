package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Semester;

public interface ScheduleRepository {

	void delete(String id) throws Exception;

	List<Schedule> findAll();

	List<Schedule> findBy(String year);

	Schedule findById(String id);

	String getId(String year, Semester semester);

	void store(Schedule schedule) throws Exception;

}
