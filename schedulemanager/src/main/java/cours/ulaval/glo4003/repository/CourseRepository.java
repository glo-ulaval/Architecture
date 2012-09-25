package cours.ulaval.glo4003.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.model.CoursesPool;

public class CourseRepository {

	@Autowired
	@Qualifier("CourseBeanRetriever")
	private CourseRetriever courseRetriever;

	public void setCourseRetriever(CourseRetriever courseRetriever) {
		this.courseRetriever = courseRetriever;
	}

	public CourseRetriever getCourseRetriever() {
		return courseRetriever;
	}

	public CoursesPool getAll() throws Exception {
		return courseRetriever.getCourses();
	}
}
