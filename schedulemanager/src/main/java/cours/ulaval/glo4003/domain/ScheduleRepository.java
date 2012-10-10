package cours.ulaval.glo4003.domain;

import java.util.List;

public interface ScheduleRepository {

	public List<Schedule> findAll();

	public List<Schedule> findBy(String year);

	public Schedule findById(String id);

	public void store(Schedule schedule) throws Exception;

	public void delete(String id) throws Exception;

}
