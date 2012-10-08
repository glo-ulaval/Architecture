package cours.ulaval.glo4003.model;

import java.util.List;

public interface CourseRepository {

	public List<Course> findAll()
			throws Exception;

	public List<Course> findByOffering(Offering offering);

	public Course findByAcronym(String acronym);
}
