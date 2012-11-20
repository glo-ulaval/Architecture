package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;

public interface CourseRepository {

	// WARNING -- this method is for tests only
	void clear();

	List<Course> findAll() throws Exception;

	Course findByAcronym(String acronym);

	List<Course> findByOffering(Offering offering);

	void store(Course course);
}
