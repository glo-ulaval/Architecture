package cours.ulaval.glo4003.model;

import java.util.List;

public interface CourseRepository {

	public List<Course> findAll() throws Exception;
}
