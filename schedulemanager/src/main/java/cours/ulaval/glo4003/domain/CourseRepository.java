package cours.ulaval.glo4003.domain;

import java.util.List;

public interface CourseRepository {

	public List<Course> findAll()
			throws Exception;

	public List<Course> findByOffering(Offering offering);

	public Course findByAcronym(String acronym);

	public void store(Course course);
}
