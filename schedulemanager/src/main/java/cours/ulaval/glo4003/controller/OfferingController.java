package cours.ulaval.glo4003.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.model.Offering;
import cours.ulaval.glo4003.model.OfferingRepository;

@Controller
@RequestMapping(value = "/offering")
public class OfferingController {

	private static final String SUCCESS = "success";

	@Inject
	CourseRepository courseRepository;

	@Inject
	OfferingRepository offeringRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView offering()
			throws Exception {
		ModelAndView mv = new ModelAndView("offering");
		mv.addObject("years", offeringRepository.findYears());
		return mv;
	}

	@RequestMapping(value = "/{year}", method = RequestMethod.GET)
	public ModelAndView offeringByYear(@PathVariable String year)
			throws Exception {

		Offering offering = offeringRepository.find(year);

		List<Course> courses = courseRepository.findByOffering(offering);

		ModelAndView mv = new ModelAndView("offeringbyyear");
		mv.addObject("year", year);
		mv.addObject("courses", courses);

		return mv;
	}

	@RequestMapping(value = "/deletecourse")
	public ModelAndView deleteCourse(@RequestParam(required = true, value = "year") String year,
			@RequestParam(required = true, value = "acronym") String acronym) {

		ModelAndView mv = new ModelAndView("offeringbyyear");

		try {
			Offering offering = offeringRepository.find(year);
			offering.removeCourse(acronym);

			List<Course> courses = courseRepository.findByOffering(offering);

			mv.addObject("year", year);
			mv.addObject("courses", courses);
			mv.addObject("error", SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/availablecourses")
	public ModelAndView availableCourses(@RequestParam(required = true, value = "year") String year)
			throws Exception {

		ModelAndView mv = new ModelAndView("availablecourses");
		mv.addObject("year", year);
		mv.addObject("courses", courseRepository.findAll());

		return mv;
	}

	@RequestMapping(value = "/addcourse")
	public ModelAndView addCourseFromAvailableCourses(@RequestParam(required = true, value = "year") String year,
			@RequestParam(required = true, value = "acronym") String acronym) {

		ModelAndView mv = new ModelAndView("offeringbyyear");

		try {
			Offering offering = offeringRepository.find(year);
			offering.addCourse(acronym);

			List<Course> courses = courseRepository.findByOffering(offering);

			mv.addObject("year", year);
			mv.addObject("courses", courses);
			mv.addObject("error", SUCCESS);
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}

		return mv;
	}
}
