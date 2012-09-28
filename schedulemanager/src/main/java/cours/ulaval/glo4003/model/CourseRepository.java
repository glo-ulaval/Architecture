package cours.ulaval.glo4003.model;

import cours.ulaval.glo4003.persistence.CoursesDTO;

public interface CourseRepository {

	public CoursesDTO findAll() throws Exception;
}
