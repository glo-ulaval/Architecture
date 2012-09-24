package cours.ulaval.glo4003.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.utils.ResourcesLoader;

public class CourseBeanRetrieverIntegrationTest {

	@Test
	public void canGetCourses() throws Exception {
		CourseBeanRetriever retriever = new CourseBeanRetriever();
		retriever.setResourcesLoader(new ResourcesLoader());

		CoursesPool pool = retriever.getCourses();

		assertEquals(1, pool.getCoursesCount());
	}
}
