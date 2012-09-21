package cours.ulaval.glo4003.repository;

import cours.ulaval.glo4003.model.Course;

public interface ICourseRetriever {

	public Iterable<Course> getCourses();
}
