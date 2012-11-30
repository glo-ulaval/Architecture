package cours.ulaval.glo4003.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.repository.CourseRepository;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Inject
	private CourseRepository courseRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(required = true, value = "keywords") String keywords) {
		ModelAndView mv = new ModelAndView("searchresults");

		mv.addObject("keywords", keywords);
		return mv;
	}
}
