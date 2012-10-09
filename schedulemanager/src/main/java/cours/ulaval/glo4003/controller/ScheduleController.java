package cours.ulaval.glo4003.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.domain.CourseRepository;
import cours.ulaval.glo4003.domain.OfferingRepository;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Inject
	private CourseRepository courseRepository;

	@Inject
	private OfferingRepository offeringRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView schedule() {
		List<String> years = new ArrayList<String>();
		years.add("2011-2012");
		years.add("2012-2013");

		ModelAndView mv = new ModelAndView("schedule");
		mv.addObject("years", years);
		return mv;
	}

	@RequestMapping(value = "/{year}", method = RequestMethod.GET)
	public ModelAndView scheduleByYear(@PathVariable String year)
			throws Exception {
		ModelAndView mv = new ModelAndView("schedulebyyear");
		mv.addObject("year", year);

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addSchedule()
			throws Exception {
		ModelAndView mv = new ModelAndView("addschedule");
		mv.addObject("years", offeringRepository.findYears());

		return mv;
	}

	@RequestMapping(value = "/add/{year}", method = RequestMethod.GET)
	public ModelAndView addSchedule(@PathVariable String year)
			throws Exception {
		ModelAndView mv = new ModelAndView("createschedule");
		mv.addObject("year", year);
		mv.addObject("courses", courseRepository.findByOffering(offeringRepository.find(year)));

		return mv;
	}

	@RequestMapping(value = "/add/{year}/addsection", method = RequestMethod.GET)
	public ModelAndView addSection(@PathVariable String year, @RequestParam(required = true, value = "acronym") String acronym) {
		ModelAndView mv = new ModelAndView("addsection");
		mv.addObject("acronym", acronym);
		mv.addObject("course", courseRepository.findByAcronym(acronym));

		return mv;
	}
}