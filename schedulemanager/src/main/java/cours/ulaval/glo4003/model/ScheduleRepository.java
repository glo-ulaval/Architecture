package cours.ulaval.glo4003.model;

import java.util.List;

public interface ScheduleRepository {

	public List<Schedule> findAll();

	public List<Schedule> findByYear();

	public Schedule findById(String id);
}
