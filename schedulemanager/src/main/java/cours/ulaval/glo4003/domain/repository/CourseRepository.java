package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;

public interface CourseRepository {

	public List<Course> findAll()
			throws Exception;

	public List<Course> findByOffering(Offering offering, Semester semester);

	public Course findByAcronym(String acronym);

	public void store(Course course);
}
