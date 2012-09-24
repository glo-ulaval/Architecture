package cours.ulaval.glo4003.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import cours.ulaval.glo4003.model.Course;

@Component
public interface ICourseRetriever {

	Collection<Course> getCourses() throws Exception;
}
