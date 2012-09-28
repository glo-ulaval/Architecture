package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.model.CourseRepository;

@Controller
public class CourseOfferingController {

	@Inject
	CourseRepository courseRepository;

	@RequestMapping(value = "/courseoffering")
	public ModelAndView courseoffering() throws Exception {

		ModelAndView mv = new ModelAndView("courseoffering");
		mv.addObject("list", courseRepository.findAll().getCourses());
		return mv;
	}
}
