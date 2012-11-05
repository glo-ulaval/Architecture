package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.TimeDedicated;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

	@Inject
	private CourseRepository repository;

	@RequestMapping(value = "/{acronym}", method = RequestMethod.GET)
	public ModelAndView getDescription(@PathVariable String acronym) {
		ModelAndView mv = new ModelAndView("coursedescription");

		Course course = repository.findByAcronym(acronym);
		TimeDedicated timeDedicated = course.getTimeDedicated();

		mv.addObject("acronym", acronym);
		mv.addObject("title", course.getTitle());
		mv.addObject("description", course.getDescription());
		mv.addObject("cycle", course.getCycle());
		mv.addObject("timeInClass", timeDedicated.getCourseHours());
		mv.addObject("timeInLab", timeDedicated.getLabHours());
		mv.addObject("timeAtHome", timeDedicated.getOtherHours());
		mv.addObject("prerequisites", course.getPrerequisites());

		return mv;
	}
}
