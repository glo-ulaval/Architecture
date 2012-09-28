package cours.ulaval.glo4003.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.model.Cycle;

@Controller
public class CourseOfferingController {

	@RequestMapping(value = "/courseoffering")
	public ModelAndView courseoffering() {

		CoursesPool coursesPool = new CoursesPool();
		// try {
		// coursesPool = courseRepository.getAll();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		ArrayList<Course> courseOffering = new ArrayList<Course>();
		courseOffering.add(new Course("GLO-1010", "Pratique du génie logiciel",
				1, "Description du cours", Cycle.FIRST, null));
		courseOffering.add(new Course("GLO-1901",
				"Introduction à la programmation avec Python", 3,
				"Description du cours", Cycle.FIRST, null));
		courseOffering.add(new Course("GLO-2003",
				"Introduction aux processus du génie logiciel", 3,
				"Description du cours", Cycle.FIRST, null));
		courseOffering.add(new Course("GLO-2100",
				"Algorithmes et structures de données pour ingénieurs", 3,
				"Description du cours", Cycle.FIRST, null));
		courseOffering.add(new Course("GLO-4002",
				"Qualité et métriques du logiciel", 3, "Description du cours",
				Cycle.FIRST, null));

		ModelAndView mv = new ModelAndView("courseoffering");
		mv.addObject("list", courseOffering);
		return mv;
	}

}
