package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;

public interface CourseRepository {

	public List<Course> findAll()
			throws Exception;

	public List<Course> findByOffering(Offering offering);

	public Course findByAcronym(String acronym);

	public void store(Course course);

	// WARNING -- this method is for tests only
	public void clear();
}
